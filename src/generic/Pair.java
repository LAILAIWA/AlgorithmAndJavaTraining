package generic;

import java.io.Serializable;

/**
 * @program: datastructure
 * @description: 简单泛型类
 * @author: 来建培
 * @create: 2019-02-18
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static <T> T getMiddle(T... a) {//泛型方法
        return a[a.length / 2];
    }

    //对类型的约束
    public static <T extends Comparable> T min(T[] a) {
        //T是Comparable的子类型(subtype)，extends表示二者关系更接近于继承。
        // 这类继承可以一对多(接口情况下)，T extends A & B，擦除时会用A来代替T，所以方法较少的接口应放到末尾。
        if (a == null || a.length == 0) return null;
        T min = a[0];
        for (int i = 1; i < a.length; i++)
            if (min.compareTo(a[i]) > 0) min = a[i];
        return min;
    }

    @Override
    public String toString() {
        return super.toString() + " first[" + first + "] second[" + second + "]";
    }
}
