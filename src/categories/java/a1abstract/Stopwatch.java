package categories.java.a1abstract;

/**
 * 计时器
 */
public class Stopwatch {
    private final long start;

    public Stopwatch(){
        this.start = System.currentTimeMillis();
    }

    public double elapseTime(){
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
