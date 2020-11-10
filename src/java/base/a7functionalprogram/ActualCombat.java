package java.base.a7functionalprogram;

import java.util.*;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.LongStream;

public class ActualCombat {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1,4,9);
//        subsets(list);

        DoubleUnaryOperator converterCtoF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator converterUSDtoGBP= curriedConverter(0.6, 0);
        DoubleUnaryOperator converterKmtoMi = curriedConverter(0.6214, 0);
        double c = converterCtoF.applyAsDouble(100);//212.0
        double gbp = converterUSDtoGBP.applyAsDouble(1000);//600.0
        double km = converterKmtoMi.applyAsDouble(1_000_000);//621400.0
        System.out.println("c：" + c);
        System.out.println("gbp：" + gbp);
        System.out.println("km：" + km);
    }

    /**
     * 迭代式阶乘计算
     */
    static int factorialIterative(int n){
        int r = 1;
        for(int i = 1;i <= n;i++){
            r *= i;
        }
        return r;
    }

    /**
     * 递归式阶乘计算
     */
    static long factorialRecursive(long n){
       return n == 1 ? 1 : n * factorialRecursive(n-1);
    }

    /**
     * Stream式阶乘计算
     */
    static long factorialStreams(long n){
        return LongStream.rangeClosed(1,n)
                .reduce(1, (long a, long b) -> a * b);
    }

    /**
     * 递归式阶乘计算: 尾-调优化
     */
    static long factorialTailRecursive(long n){
        return factorialHelper(1,n);
    }
    static long factorialHelper(long acc, long n){
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }

    /**
     * 给定一个集合，求集合所有子集
     * @param list 集合
     * @return 子集数组
     */
    static List<List<Integer>> subsets(List<Integer> list){
        if(list.isEmpty()){
            //如果输入集合为空，其子集只包含空集
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        System.out.println("first: " + first);
        List<Integer> rest = list.subList(1, list.size());

        //否则就取出一个元素first，找出剩余部分的所有子集，并将其赋予subans。subans构成了结果的另一半。
        List<List<Integer>> subans = subsets(rest);
        //答案的另一半是subans2，它包含了subans中的所有集合，但是经过调整，在每个列表的第一个元素之前添加了first
        List<List<Integer>> subans2 = insertAll(first,subans);
        //将两个答案整合一起完成任务
        return concat(subans,subans2);
    }

    /**
     * 将移出元素和当前子集组合
     * @param first 移出的当前首元素
     * @param lists 已生成的子集数组
     * @return 当前子集数组
     */
    static List<List<Integer>> insertAll(Integer first,
                                         List<List<Integer>> lists){
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> list : lists){
            //复制列表，从而使你有机会对其进行添加操作。
            //即使底层是可变的，你也不应该复制底层的结构（不过Integer底层是不可变的）
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        System.out.println("insertAll: ");
        result.forEach(System.out::println);
        return result;
    }

    /**
     * 合并子集数组元素
     * @param a 数组a
     * @param b 数组b
     * @return 最终结果
     */
    static List<List<Integer>> concat(List<List<Integer>> a,
                                      List<List<Integer>> b){
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        System.out.println("concat: ");
        r.forEach(System.out::println);
        return r;
    }

    /**
     * 单位转换
     * @param x 需要转换的值
     * @param f 转换因子
     * @param b 基线值
     * @return 结果
     */
    static double converter(double x, double f, double b){
        return x * f + b;
    }

    /**
     * 工厂方法生产单位转换公式
     * @param f 转换因子
     * @param b 基线值
     * @return 单位转换公式
     */
    static DoubleUnaryOperator curriedConverter(double f, double b){
        return (double x) -> x * f + b;
    }
}
