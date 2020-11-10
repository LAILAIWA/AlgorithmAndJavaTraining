package java.base.a7functionalprogram.delaycalculation;

import java.util.function.Predicate;

/**
 * 基本链表
 */
public interface MyList<T> {
    T head();

    MyList<T> tail();

    MyList<T> filter(Predicate<T> p);


    default boolean isEmpty(){
        return true;
    }


}
