package java.base.a13thread;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-05-18
 */
public class Test1 {
    public static void main(String[] args){
        Person p = new Person("a",1);
        System.out.println(p.toString());
    }

    public void change(Person p){
        p = new Person("b",2);
    }
}
