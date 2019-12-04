package categories.java.a7stream.observer;

public class Test {
    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new Lemonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
        //Yet another news in London... The queen said her favourite book is Java 8 in Action!

        //通过Lambda表达式
        Feed f1 = new Feed();
        f1.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        f1.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });
        f1.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("wine")){
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });
        f1.notifyObservers("The queen said her favourite book is Java 8 in Action!");

    }
}
