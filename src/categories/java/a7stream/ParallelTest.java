package categories.java.a7stream;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 并行流相关代码
 */
public class ParallelTest {
    public static void main(String[] args) {

        sequentialSum(5);
        //将顺序流转为并行流
        parallelSum(5);

//        System.out.println("Sequential sum done in " + measureSumPerf(ParallelTest::sequentialSum, 10_000_000) + " mscs");
//        System.out.println("Iterative sum done in " + measureSumPerf(ParallelTest::iterativeSum, 10_000_000) + " mscs");
//        System.out.println("Parallel sum done in " + measureSumPerf(ParallelTest::parallelSum, 10_000_000) + " mscs");
//        System.out.println("Range sum done in " + measureSumPerf(ParallelTest::rangedSum, 10_000_000) + " mscs");
//        System.out.println("Parallel range sum done in " + measureSumPerf(ParallelTest::parallelRangedSum, 10_000_000) + " mscs");
//
//        System.out.println("Side effect sum done in " + measureSumPerf(ParallelTest::sideEffectSum, 10_000_000) + " mscs");
//        System.out.println("Side effect parallel sum done in " + measureSumPerf(ParallelTest::sideEffectParallelSum, 10_000_000) + " mscs");
//
//        System.out.println("Fork join sum done in " + measureSumPerf(ParallelTest::forkJoinSum, 10_000_000) + " mscs");

        //传统迭代版本
        final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita " +
                "mi  ritrovai in una  selva oscura" +
                " che la  dritta via era   smarrita";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        //函数式版本
        //首先把字符串转换为流
        Stream<Character> stream = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);
        //然后进行归约计算字数
        //保留两个变量状态：当前统计数目，上个字符是否空格
        System.out.println("Found " + countWords(stream) + " words");

        //尝试并行化，发现结果不正确
        Stream<Character> stream1 = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream1.parallel()) + " words");

        //确保String只在词尾切分，我们只能自定义Spliterator，这个迭代器只会在两个词间进行切分
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        //第二个参数为true表示创建并行流
        Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream2) + " words");
    }

    /**
     * 计算从1到给定数字的和
     * @param n 值
     * @return 和
     */
    public static long sequentialSum(long n){
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 迭代计算从1到给定数字的和
     * @param n 值
     * @return 和
     */
    public static long iterativeSum(long n){
        long result = 0;
        for(long i = 1L;i <= n;i++){
            result += i;
        }
        return result;
    }

    /**
     * 并行计算从1到给定数字的和
     * @param n 值
     * @return 和
     */
    public static long parallelSum(long n){
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() //将流转为并行流
                .reduce(0L, Long::sum);
    }

    /**
     * 测量求和函数开销
     * @param adder 函数
     * @param n 值
     * @return 开销
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n){
        long fastest = Long.MAX_VALUE;
        for(int i = 0;i < 10;i++){//测试运行10次
            long start = System.nanoTime();
            long sum = adder.apply(n);//执行函数
            long duration = (System.nanoTime() - start) / 1_000_000;//取运行时间的毫秒值
            //System.out.println("Result<" + (i+1) + ">: " + sum);
            if(duration < fastest)//检查执行是否是最快的一个
                fastest = duration;
        }
        return fastest;
    }

    /**
     * 没有装箱的求和函数
     * @param n 值
     * @return 和
     */
    public static long rangedSum(long n){
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    /**
     * 并行没有装箱的计算从1到给定数字的和
     * @param n 值
     * @return 和
     */
    public static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 通过累加器类对前n个自然数求和
     * @param n 值
     * @return 和
     */
    public static long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 通过累加器类对前n个自然数求和（并行化）
     * @param n 值
     * @return 和
     */
    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 通过分支合并框架对前n个自然数求和
     * @param n 值
     * @return 和
     */
    public static long forkJoinSum(long n){
        //LongStream生成前n个自然数的数组
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        //根据数组实例化ForkJoinTask
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        //实例化ForkJoinPool并把任务交给其调用方法
        return new ForkJoinPool().invoke(task);
    }

    /**
     * 统计字符串中单词个数
     * @param s 字符串
     * @return 单词个数
     */
    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()){//逐个遍历字符串的所有字符
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else {
                if(lastSpace) counter++;//上个字符是空格，而当前字符不是空格，计数器+1
                lastSpace = false;
            }
        }
        return counter;
    }

    /**
     * 通过自定义WordCounter对字符流进行计算
     * @param stream 流
     * @return 单词个数
     */
    public static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0,true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

}

