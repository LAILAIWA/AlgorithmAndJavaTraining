package categories.java.a2generic;

/**
 * @program: datastructure
 * @description: 子类
 * @author: 来建培
 * @create: 2019-02-21
 */
public class Child extends Parent<String> {
    public void test(String t){
        System.out.println("Child t[" + t + "]");
    }
}
