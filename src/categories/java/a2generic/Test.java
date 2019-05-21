package categories.java.a2generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: datastructure
 * @description: 测试
 * @author: 来建培
 * @create: 2019-02-18
 */
public class Test {
    public static void main(String[] args) {
        //创建泛型类对象，指定数据类型，可以把泛型类看作工厂类
        Pair<String> pair = new Pair<>();
        Pair<String> compare = new Pair<>();
        String[] strings = {"Mary", "had", "a", "little", "lamb"};
//        System.out.println("min: [" + findMaxAndMin(strings).getFirst() + "]");
//        System.out.println("max: [" + findMaxAndMin(strings).getSecond() + "]");
        System.out.println("middle: [" + Pair.getMiddle(strings) + "]");
        System.out.println("middle: [" + Pair.getMiddle("abc", "def", "ghi") + "]");
        System.out.println("middle: [" + Pair.getMiddle(3.14, 1234, 0) + "]");
        //编译器如何判断泛型方法的调用类型？
        System.out.println("middle: [" + Pair.getMiddle("asda", 0, null) + "]");

        Child child = new Child();
        Parent<String> parent = child;
        parent.test("This is a categories.java.a3string");

//        if(child instanceof Parent<String>);
//        Parent<String>[] table = new Parent<String>[10];
//        table[0] = "Hello";
        Parent<String>[] table1 = (Parent<String>[]) new Parent<?>[10];
        table1[0] = new Parent<String>();
        Object[] objects1 = table1;


        objects1[0] = new Parent<Integer>();

        Collection<Parent<String>> table = new ArrayList<>();
        Parent<String> p1 = new Parent<>();
        Parent<String> p2 = new Parent<>();
        addAll(table, p1, p2);

        Parent<String>[] table2 = toArray(p1, p2);
        Object[] objects2 = table1;
        objects2[0] = new Parent<Integer>();

        Parent<String> p = Parent.makeParent(String.class);

        Child[] children = new Child[10];
        Parent[] parents = children;//OK
        parents[0] = new Parent();
//        ArrayList<Child> childPair = new ArrayList<>();
//        ArrayList<Parent> parentPair = childPair;//Error
    }

//    public static <T extends Comparable> T[] minmax(T[] a){
//        T[] mm = new T[2];//Error
//    }

//    @SuppressWarnings("unchecked")
//    public static Pair<String,String> findMaxAndMin(String[] strings){
//        if(strings == null || strings.length == 0) return null;
//        String min = strings[0];
//        String max = strings[0];
//        for(int i = 1;i < strings.length;i++){
//            if(strings[i].compareTo(min) < 0) min = strings[i];
//            if(strings[i].compareTo(max) > 0) max = strings[i];
//        }
//        return new Pair<>(min,max);
//    }

    @SafeVarargs
    public static <T> void addAll(Collection<T> col, T... ts) {
        for (T t : ts) col.add(t);
    }

    @SafeVarargs
    public static <T> T[] toArray(T... ts) {
        return ts;
    }
}
