package java.base.a7stream;

/**
 * 累加器
 */
public class Accumulator {
    public long total = 0;
    public void add(long value){
        total += value;
    }
}
