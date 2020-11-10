package java.base.a7future.bestpriceselector;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    //创建一个线程数为100以及商店数目中较小数字数量的线程池
    private static final Executor executor = Executors.newFixedThreadPool(
            Math.min(shops.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    //使用守护线程，不会阻止程序的关停
                    t.setDaemon(true);
                    return t;
                }
            }
    );
    public static void main(String[] args) {
//        Shop shop = new Shop("BestShop");
//        long start = System.nanoTime();
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Invocation returned after " + invocationTime + " msecs");
//
//        //执行更多任务，比如查询其他商店
//        doSomethingElse();
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n",price);
//        }catch (Exception ex){
//            throw new RuntimeException(ex);
//        }
//        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("Price returned after " + retrievalTime + " msecs");

        long start = System.nanoTime();
        List<String> result = getPricesAsync("myPhone27S");
        System.out.println(result);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        findPricesStream("myPhone27S").map(f -> f.thenAccept(System.out::println));
        CompletableFuture[] futures =
                findPricesStream("myPhone27S")
                        .map(f -> f.thenAccept(System.out::println))
                        .toArray(size -> new CompletableFuture[size]);
        //allOf方法接受一个由CompletableFuture构成的数组，数组对象执行完毕后，返回一个CompletableFuture<Void>对象
        CompletableFuture.allOf(futures).join();
    }

    public static void doSomethingElse(){
        try{
            System.out.println("doSomethingElse");
            Thread.sleep(1000L);
        }catch (InterruptedException ex){
            throw new RuntimeException();
        }
    }

    /**
     * 查询产品商店名和价格
     * @param product 产品
     * @return
     */
    public static List<String> findPrices(String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 查询产品商店名和价格—异步
     * @param product 产品
     * @return
     */
    public static List<String> findPricesAsync(String product){
        //通过CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFuture =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " + shop.getPrice(product),executor)
                        ).collect(Collectors.toList());
        //等待所有异步操作结束，join方法与Future.get()含义相同，但不会抛出检测的异常
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    /**
     * 获取折扣后价格
     * @param product 产品
     * @return 价格
     */
    public static List<String> getPrices(String product){
        return shops.parallelStream().map(shop -> shop.getPrice(product))
                //通过Quote对shop返回字符串进行解析
                .map(Quote::parse)
                //调用applyDiscount方法为每个Quote申请折扣
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }
    /**
     * 获取折扣后价格—异步
     * @param product 产品
     * @return 价格
     */
    public static List<String> getPricesAsync(String product){
        List<CompletableFuture<String>> priceFuture =
                shops.stream()
                        //以异步的方式取得每个shop中指定产品的价格
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product),executor))
                        //当Quote对象存在时，对返回值进行转换
                        .map(future -> future.thenApply(Quote::parse))
                        //使用另一个异步任务构造期望的Future，申请折扣
                        .map(future -> future.thenCompose(
                                quote -> CompletableFuture.supplyAsync(
                                        () -> Discount.applyDiscount(quote),executor)))
                        .collect(Collectors.toList());
        //等待流中所有Future执行完毕，提取各自返回值
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

//    /**
//     * 产品价格汇率转换
//     */
//    public Future<Double> converter(Shop shop,String product){
//        Future<Double> futurePriceUSD =
//                CompletableFuture.supplyAsync(
//                        () -> shop.getPrice(product)
//                ).thenCombine(
//                        CompletableFuture.supplyAsync(
//                                () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)
//                        ), (price, rate) -> price * rate
//                );
//        return futurePriceUSD;
//    }
//
//    /**
//     * 产品价格汇率转换—Future实现版本
//     */
//    public Future<Double> converterFuture(Shop shop,String product){
//        //创建ExecutorService将任务提交到线程池
//        ExecutorService executor = Executors.newCachedThreadPool();
//        //创建一个查询汇率转换的Future
//        final Future<Double> futureRate = executor.submit(new Callable<Double>() {
//            @Override
//            public Double call() throws Exception {
//                return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
//            }
//        });
//        Future<Double> futurePriceUSD = executor.submit(new Callable<Double>() {
//            @Override
//            public Double call() throws Exception {
//                double priceInEUR = shop.getPrice(product);
//                return priceInEUR * futureRate.get();
//            }
//        });
//        return futurePriceUSD;
//    }

    /**
     * 重构findPrices方法返回一个Future构成的流
     * @param product
     * @return
     */
    public static Stream<CompletableFuture<String>> findPricesStream(String product){
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product),executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote),executor
                        )));
    }
}
