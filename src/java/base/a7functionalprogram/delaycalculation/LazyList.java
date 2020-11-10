package java.base.a7functionalprogram.delaycalculation;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 延迟列表
 */
public class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * 创建由数字构成的无限延迟列表
     */
    public static LazyList<Integer> from(int n){
        return new LazyList<>(n, () -> from(n+1));
    }

    @Override
    public MyList<T> filter(Predicate<T> p){
        return isEmpty() ?
                this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
    }
}
