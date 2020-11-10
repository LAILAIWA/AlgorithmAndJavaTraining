package java.base.a7future.bestpriceselector;

public class ExchangeService {
    public enum Money{

        EUR(8),USD(5);

        private final int rate;

        Money(int rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money a, Money b){
        return a.rate / b.rate;
    }
}
