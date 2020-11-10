package java.base.a7stream;

import java.util.*;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );

        //Question 1 : 找出2011年发生的所有交易，并按交易额升序排序
        List<Transaction> result1 = transactions.stream()
                .filter(t -> 2011 == t.getYear())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("------------------------------\nQuestion 1 : ");
        System.out.println(result1);

        //Question 2 : 交易员都在哪些不同的城市工作过
//        List<String> result2 = transactions.stream()
//                .map(Transaction::getTrader)
//                .map(Trader::getCity)
//                .distinct()
//                .collect(Collectors.toList());
        Set<String> result2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println("------------------------------\nQuestion 2 : ");
        System.out.println(result2);

        //Question 3 : 查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> result3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("------------------------------\nQuestion 3 : ");
        System.out.println(result3);

        //Question 4 : 返回所有交易员的姓名字符串，按字母顺序排序
//        String result4 = transactions.stream()
//                .map(t -> t.getTrader().getName())
//                .distinct()
//                .sorted()
//                .reduce("",(s1,s2) -> s1 + s2);
        String result4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println("------------------------------\nQuestion 4 : ");
        System.out.println(result4);

        //Question 5 : 有没有交易员是在米兰工作的
        boolean result5 = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t->"Milan".equals(t.getCity()));
        System.out.println("------------------------------\nQuestion 5 : ");
        System.out.println(result5);

        //Question 6 : 打印生活在剑桥的交易员的所有交易额
        System.out.println("------------------------------\nQuestion 6 : ");
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //Question 7 : 所有交易中，最高的交易额是多少
        Optional<Integer> result7 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println("------------------------------\nQuestion 7 : ");
        System.out.println(result7);

        //Question 8 : 找到交易额最小的交易
        Optional<Transaction> result8 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("------------------------------\nQuestion 8 : ");
        System.out.println(result8);
    }
}
