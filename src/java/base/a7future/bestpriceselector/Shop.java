package java.base.a7future.bestpriceselector;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    /**
     * 获取价格
     * @param product 产品
     * @return 价格
     */
//    public double getPrice(String product){
//        //需要查询商店的数据库
//        //可能要联系其他外部服务，比如商店的供应商，和制造商相关的推广折扣
//        //ToDo
//        return calculatePrice(product);
//    }

    /**
     * 计算获取价格
     * @param product 产品
     * @return 价格
     */
    public static double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟耗时操作，1s延迟
     */
    public static void delay(){
        try{
            Thread.sleep(1000L);
        }catch (InterruptedException ex){
            throw new RuntimeException();
        }
    }

    private static final Random random = new Random();
    /**
     * 模拟耗时操作，0.5s - 2.5s随机延迟
     */
    public static void randomDelay(){
        int delay = 500 + random.nextInt(2000);
        try{
            Thread.sleep(delay);
        }catch (InterruptedException ex){
            throw new RuntimeException();
        }
    }


    /**
     * 获取价格—异步
     * @param product 产品
     * @return 价格-暂不可知
     */
    public Future<Double> getPriceAsync(String product){
//        //创建CompletableFuture对象，它会包含计算的结果
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        //开启一个新的线程来执行计算
//        new Thread(() -> {
//            try{
//                double price = calculatePrice(product);
//                //设置Future的返回值
//                futurePrice.complete(price);
//            }catch (Exception ex){
//                //抛出导致失败的异常，完成此次Future操作
//                futurePrice.completeExceptionally(ex);
//            }
//        }).start();
//        //无需等待还未结束的计算，直接返回Future<Double>对象
//        return futurePrice;

        //通过supplyAsync优化
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    /**
     * 获取商品以及随机折扣
     * @param product 产品
     * @return 价格
     */
    public String getPrice(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",name,price,code);
    }
}
