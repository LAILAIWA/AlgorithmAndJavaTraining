package categories.java.a7stream.observer;

/**
 * 观察者—纽约时报
 */
public class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
