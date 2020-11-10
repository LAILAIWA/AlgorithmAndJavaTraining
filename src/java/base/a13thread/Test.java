package java.base.a13thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-16
 */
public class Test {
    private static List<Person> list;

    public static Unsafe getUnsafeInstance() throws Exception{
        Field unsafeStaticField =
                Unsafe.class.getDeclaredField("theUnsafe");
        unsafeStaticField.setAccessible(true);
        return (Unsafe) unsafeStaticField.get(Unsafe.class);
    }

    public static void main(String[] args) {
//        try {
//            String s = "abc";
//            //保存s的引用
//            s.intern();
//
//            //此时s1==s，地址相同
//            String s1 = "abc";
//
//            Unsafe u = getUnsafeInstance();
//
//            //获取s的实例变量value
//            Field valueInString = String.class.getDeclaredField("value");
//
//            //获取value的变量偏移值
//            long offset = u.objectFieldOffset(valueInString);
//
//            //value本身是一个char[],要修改它元素的值，仍要获取baseOffset和indexScale
//            long base = u.arrayBaseOffset(char[].class);
//
//            long scale = u.arrayIndexScale(char[].class);
//
//            //获取value
//            char[] values = (char[]) u.getObject(s, offset);
//
//            //为value赋值
//            u.putChar(values, base + scale, 'c');
//
//            System.out.println("s:"+s+" s1:"+s1);
//
//            //将s的值改为 abc
//            s = "abc";
//
//            String s2 = "abc";
//
//            String s3 = "abc";
//
//            System.out.println("s:"+s+" s1:"+s1);
//            System.out.println("s2:"+s2+" s3:"+s3);
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }

        list = new ArrayList<>();
        List<String> staff = new LinkedList<>();
        staff.add("张三");
        staff.add("李四");
        staff.add("王五");
        ListIterator<String> iterator = staff.listIterator();
        String first = iterator.next();
        System.out.println("first:" + first);
        String second = iterator.next();
        System.out.println("second:" + second);
        String pre1 = iterator.previous();
        System.out.println("pre1:" + pre1);
        iterator.remove();
        iterator.forEachRemaining(str->System.out.println(str + " "));

//        add(1,10);
//        add(4,7);
//        add(24,3);
//
//        for (int j = 0; j < list.size(); j++)
//            System.out.println(list.get(j).getName());

//        Person person1 = new Person("Tom");
//        Person person2 = new Person("Lee");
//
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                person1.printName();
////            }
////        }).start();
////
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                person1.printNameTwo();
////            }
////        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Person.printStatic();
//            }
//        }).start();
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Person.printStaticTwo();
//            }
//        }).start();

    }

    public static void add(int start,int num){
        for(int i = 0;i < num;i++){
            Person person = new Person(start+i+"");
            list.add(person);
        }
    }

}
