package java.base.a13thread;

import java.util.concurrent.Callable;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-05-22
 */
public class MyCallable implements Callable<Integer> {
    private int count = 0;
    @Override
    public Integer call() {
        int sum = 0;
        for(;count < 100;count++){
            System.out.println(Thread.currentThread().getName() + " " + count);
            sum++;
        }
        return sum;
    }
}
