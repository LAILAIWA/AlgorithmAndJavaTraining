package categories.java.a1innerClass;

import categories.java.a11proxy.TraceHandler;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-15
 */
public class OuterTest {

    public static void main(String[] args){
        Object[] elements = new Object[1000];
        for(int i = 0;i < elements.length;i++){
            Integer value = i+1;
            elements[i] = Proxy.newProxyInstance(
                    null, new Class[]{Comparable.class},new TraceHandler(value));
        }

        Integer key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements,key);
        if(result > 0) System.out.println(elements[result]);
    }
}
