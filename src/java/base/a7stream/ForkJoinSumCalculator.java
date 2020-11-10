package java.base.a7stream;

import java.util.concurrent.RecursiveTask;

/**
 * 分支合并框架进行并行求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;//要进行求和的数组
    private final int start;//子任务处理的数组起始位置
    private final int end;//子任务处理的数组终止位置

    public static final long THRESHOLD = 10_000;//停止分解子任务的数组大小

    @Override
    protected Long compute() {
        int length = start - end;//要进行求和的部分大小
        if(length <= THRESHOLD){//如果大小小于或等于阈值，就执行顺序计算结果
            return computeSequentially();
        }
        //创建一个子任务为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start + length/2);
        //利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        //创建一个子任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start + length/2,end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果未完成则等待
        Long leftResult = leftTask.join();
        //任务结果为两个子任务结果合并
        return leftResult + rightResult;
    }

    /**
     * 顺序求和
     * @return 和
     */
    private long computeSequentially(){
        long sum = 0;
        for(int i = start;i < end;i++){
            sum += numbers[i];
        }
        return sum;
    }

    public ForkJoinSumCalculator(long[] numbers){//公共构造器用于创建主任务
        this(numbers,0,numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {//私有构造器用于以递归的方式创建子任务
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
}
