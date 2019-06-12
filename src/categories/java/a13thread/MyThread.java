package categories.java.a13thread;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-05-21
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("own thread is running");
    }
}
