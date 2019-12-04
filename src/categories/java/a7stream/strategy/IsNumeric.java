package categories.java.a7stream.strategy;

/**
 * 策略实现-是否数字
 */
public class IsNumeric implements ValidationStratery {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
