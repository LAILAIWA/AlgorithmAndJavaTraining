package java.base.a7future.bestpriceselector;

/**
 * 折扣
 */
public class Discount {
    public enum Code{
        NONE(0),SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        //将折扣代码应用于商品原价
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),quote.getDiscountCode());
    }

    private static double apply(double price, Code code){
        //模拟Discount服务的响应延迟
        delay();
        return price * (100 - code.percentage) / 100;
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
}
