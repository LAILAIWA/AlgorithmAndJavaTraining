package java.base.a7lambda;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-16
 */
public class Test {

    public static void main(String[] args) {
        String[] list = new String[]{"caaa", "as", "b"};
        print(list);
        BiFunction<String, String, Integer> comp = (first, second) -> first.length() - second.length();
        Arrays.sort(list, (first, second) -> first.length() - second.length());
        print(list);
        ArrayList arrayList = new ArrayList();
        Predicate notnull = e -> e == null;
        arrayList.removeIf(notnull);

        repeat(10, i -> System.out.println("CountDown: " + (9 - i)));
    }

    public static void print(String[] list) {
        for (String s : list)
            System.out.print(s + " ");
        System.out.println();
    }

    public static void countDown(int start, int delay) {
        ActionListener listener = event -> {
            System.out.println(start);
//            start--;
        };
        new Timer(delay, listener).start();
    }

    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++)
            action.accept(i);
    }
}
