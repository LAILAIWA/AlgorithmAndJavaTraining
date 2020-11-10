package java.base.a7methodreference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(170,"red"));
        apples.add(new Apple(160,"green"));
        apples.add(new Apple(120,"red"));
        apples.add(new Apple(170,"green"));

        //第一种实现，行为参数化
        apples.sort(new AppleWeightComparator());
        //sort行为被参数化了，根据策略的不同，排序结果也有相应的变化，但每一种策略都需要构建对应的Comparator
        apples.forEach(System.out::println);
        System.out.println();

        //第二种实现，引入匿名类
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //匿名类不需要构建类，但本质上仍是啰嗦的

        //第三种实现，引入Lambda
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //需要函数式接口时可以提高Lambda表达式，而Comparator<? super E>的函数描述符是(T, T) -> int，所以我们传递(Apple, Apple) -> int

        //第四种实现，引入库提供的辅助方法
        apples.sort(Comparator.comparing((Apple a) -> a.getWeight()));

        //第五种实现，引入方法引用
        apples.sort(Comparator.comparing(Apple::getWeight));

        //复合Lambda：比较器复合，谓词复合，函数复合
        //逆序
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        apples.forEach(System.out::println);
        System.out.println();
        //比较器链
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        apples.forEach(System.out::println);
        System.out.println();

        Predicate<Apple> redApple = a -> "red".equals(a.getColor());
        //negate得到非
        Predicate<Apple> notRedApple = redApple.negate();
        //and得到交
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        //or得到并
        Predicate<Apple> redAndHeavyOrGreenApple = redApple.and(a -> a.getWeight() > 150)
                                                           .or(a -> "green".equals(a.getColor()));
        apples.removeIf(redAndHeavyOrGreenApple);
        apples.forEach(System.out::println);
        System.out.println();

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        //andThen，先应用前一个函数，再应用后一个函数
        Function<Integer, Integer> h = f.andThen(g);//数学表示：g(f(x))或(g o f)(x)
        int result1 = h.apply(1);
        System.out.println(result1);//输出4

        //compose，先应用前一个函数，再应用后一个函数
        Function<Integer, Integer> i = f.compose(g);//数学表示：f(g(x))或(f o g)(x)
        int result2 = i.apply(1);
        System.out.println(result2);//输出3
    }
}
