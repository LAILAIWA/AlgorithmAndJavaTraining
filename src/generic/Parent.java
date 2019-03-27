package generic;

import java.util.function.Supplier;

/**
 * @program: datastructure
 * @description: 父类
 * @author: 来建培
 * @create: 2019-02-21
 */
public class Parent<T> {
    private T first;
    private T second;

//    public static <T> Parent<T> makeParent(Supplier<T> constr){
//        return new Parent<>(constr.get(),constr.get());
//    }

    public static <T> Parent<T> makeParent(Class<T> constr) {
        try {
//            return new Parent<>(constr.newInstance(),constr.newInstance());
            return new Parent<>(constr.getDeclaredConstructor().newInstance(), constr.getDeclaredConstructor().newInstance());
        } catch (Exception ex) {
            return null;
        }
    }

    public Parent() {
    }

    public Parent(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public void test(T t) {
        System.out.println("Parent t[" + t + "]");
    }
}
