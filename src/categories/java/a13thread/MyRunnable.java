package categories.java.a13thread;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-05-22
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
