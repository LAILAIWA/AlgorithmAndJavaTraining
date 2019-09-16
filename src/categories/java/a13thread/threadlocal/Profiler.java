package categories.java.a13thread.threadlocal;

import java.util.concurrent.TimeUnit;

public class Profiler {
    //第一次get()调用会进行初始化(若没有调用set())，每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args)throws Exception{
        Profiler.begin();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
