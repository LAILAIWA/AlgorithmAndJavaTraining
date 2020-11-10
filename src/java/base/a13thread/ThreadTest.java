package java.base.a13thread;

import java.util.concurrent.*;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-05-21
 */
public class ThreadTest {

    public static void main(String[] args) {
        //Runnable
//        Runnable runnable = () -> {System.out.println(Thread.currentThread().getName());};
////        Runnable runnable = new MyRunnable();
//        Thread thread = new Thread(runnable);
//        thread.start();

        //Thread
//        MyThread mt = new MyThread();
//        mt.start();
//        System.out.println("over");


        //Callable+FutureTask
        Callable<Integer> myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);

        for(int i = 0;i < 100;i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 30){
                Thread thread = new Thread(futureTask);
                thread.start();
            }
        }
        try{
            int sum = futureTask.get();
            System.out.println("sum = " + sum);

        }catch (InterruptedException | ExecutionException ex){
            ex.printStackTrace();
        }

        //Executors
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池里面的线程数会动态变化，并可在线程线被移除前重用
        for (int i = 1; i <= 3; i ++) {
            final  int task = i;   //10个任务
            threadPool.execute(new Runnable() {    //接受一个Runnable实例
                public void run() {
                    System.out.println(Thread.currentThread().getName() +  "  task: "+task);
                }
            });
        }

    }
}
