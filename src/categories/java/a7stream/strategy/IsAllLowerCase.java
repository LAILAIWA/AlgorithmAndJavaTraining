package categories.java.a7stream.strategy;

/**
 * 策略实现-是否全部小写
 */
public class IsAllLowerCase implements ValidationStratery {
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
