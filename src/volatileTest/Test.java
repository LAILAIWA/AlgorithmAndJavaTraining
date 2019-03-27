package volatileTest;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-01-09
 */
public class Test {
    volatile int inc = 0;

    public void increase() {
        inc++;
    }

    class IncThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().toString() + " 读取" + inc);
            for(int j=0;j<1000;j++) {
                increase();
            }
            System.out.println(Thread.currentThread().toString() + " 最终" + inc);
        };
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            IncThread thread = test.new IncThread();
            thread.start();
        }
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println("执行完");
        System.out.println(test.inc);
    }
}
