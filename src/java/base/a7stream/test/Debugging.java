package java.base.a7stream.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Debugging {
    public static void main(String[] args) {
//        List<Point> points = Arrays.asList(new Point(12, 2),null);
//        points.stream().map(p -> p.getX()).forEach(System.out::println);
        List<Integer> numbers = Arrays.asList(2,3,4,5);
        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);
        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x)) //输出来自数据源的当前元素值
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x)) //输出map操作的结果
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)) //输出经过filter操作之后，剩下的元素个数
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x)) //输出经过limit操作之后，剩下的元素个数
                .collect(Collectors.toList());
    }
}
