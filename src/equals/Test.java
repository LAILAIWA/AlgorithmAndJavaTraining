package equals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * @program: datastructure
 * @description: equals和hashcode
 * @author: 来建培
 * @create: 2018-12-26
 */
public class Test {
    public static void main(String[] args){
        Student s1 = new Student("001","张三");
        Student s2 = new Student("001","张三");
        Student s3 = new Student("002","张三");
        Student s4 = new Student("003","李四");
        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s1.equals(s4): " + s1.equals(s4));
        System.out.println("s1: " + s1.toString() + "s1.hashCode(): " + s1.hashCode());
        System.out.println("s2: " + s2.toString() + "s2.hashCode(): " + s2.hashCode());
        System.out.println("s3: " + s3.toString() + "s3.hashCode(): " + s3.hashCode());
        System.out.println("s4: " + s4.toString() + "s4.hashCode(): " + s4.hashCode());

        HashSet set = new HashSet();
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        System.out.println("HashSet: " + set.toString());
    }
}
