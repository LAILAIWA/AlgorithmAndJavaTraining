package categories.java.a7stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 质数收集器
 */
public class PrimeNumbersCollector implements Collector<Integer,
                                                        Map<Boolean, List<Integer>>,
                                                        Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        //创建了作为累加器的map，并为true和false键初始化了空列表。
        return () -> new HashMap<Boolean, List<Integer>>(){
            {
                put(true,new ArrayList<>());
                put(false,new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true),candidate)) //根据isPrime结果获取质数或非质数列表
                    .add(candidate); //将被测数添加到相应列表
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        //此收集器是无法并行运算的，因为算法本身是顺序的，所以当前combiner()无法被调用
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     * 判断是否为质数
     * @param primes 已有质数列表
     * @param candidate 当前被测数
     * @return 结果
     */
    public boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(i -> candidate % i == 0);
    }

    /**
     * 查找符合谓词的元素集合
     * @param list 元素列表
     * @param p 谓词
     * @return 符合元素集合
     */
    public <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for(A item : list){
            if(!p.test(item)){//检查列表中的当前项目是否符合谓词
                return list.subList(0, i);//不满足就返回该项目之前的前缀子列表
            }
            i++;
        }
        return list;
    }
}
