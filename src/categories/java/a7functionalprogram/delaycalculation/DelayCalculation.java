package categories.java.a7functionalprogram.delaycalculation;

import java.util.stream.IntStream;

public class DelayCalculation {
    public static void main(String[] args) {
//        IntStream numbers = numbers();
//        int head = head(numbers);
//        IntStream filtered = tail(numbers).filter(n -> n % head != 0);
//        primes(numbers);

        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10,new Empty<>()));
//        LazyList<Integer> numbers = LazyList.from(2);
//        int two = numbers.head();
//        int three = numbers.tail().head();
//        int four = numbers.tail().tail().head();
//        System.out.println(two + " " + three + " " + four);//2 3 4

        //延迟列表实现生成质数
//        LazyList<Integer> numbers = LazyList.from(2);
//        int two = primes(numbers).head();
//        int three = primes(numbers).tail().head();
//        int four = primes(numbers).tail().tail().head();
//        System.out.println(two + " " + three + " " + four);//2 3 5

        printAll(primes(LazyList.from(2)));
    }

    /**
     * 构造由数字组成的Stream
     */
    static IntStream numbers(){
        return IntStream.iterate(2, n -> n + 1);
    }

    /**
     * 取得首元素
     */
    static int head(IntStream numbers){
        return numbers.findFirst().getAsInt();
    }

    /**
     * 倒序获取元素
     */
    static IntStream tail(IntStream numbers){
        return numbers.skip(1);
    }

    /**
     * 数值流生成质数
     */
    static IntStream primes(IntStream numbers){
        int head = head(numbers);
        return IntStream.concat(
                IntStream.of(head),
                primes(tail(numbers).filter(n -> n % head != 0))
        );
    }

    /**
     * 延迟列表生成质数
     */
    public static MyList<Integer> primes(MyList<Integer> numbers){
        return new LazyList<>(
                numbers.head(),
                () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0))
        );
    }

    /**
     * 打印所有MyList元素
     */
    static <T> void printAll(MyList<T> list){
        if(list.isEmpty())
            return;
        System.out.println(list.head());
        printAll(list.tail());
    }

}
