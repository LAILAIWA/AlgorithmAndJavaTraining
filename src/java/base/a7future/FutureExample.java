package java.base.a7future;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        //创建一个ExecutorService，可以通过它向线程池提交任务
        ExecutorService executor = Executors.newCachedThreadPool();
        //向ExecutorService提交一个Callable对象
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                //以异步方式在新的线程中执行耗时的操作
                return doSomeLongComputation();
            }
        });
        //异步操作进行的同时可以做其他事情
        doSomethingElse();

        try {
            //获取异步操作的结果，如果最终被阻塞，无法得到结果，则最多等待1秒钟后退出
            Double result = future.get(1, TimeUnit.SECONDS);
        }catch (ExecutionException ex){
            //计算抛出一个异常
        }catch (InterruptedException ex){
            //当前线程在等待过程中被中断
        }catch (TimeoutException ex){
            //在Future对象完成之前已过期
        }
    }

    public static Double doSomeLongComputation(){
        return 1.0d;
    }
    public static void doSomethingElse(){
        System.out.println("doSomethingElse");
    }
}
