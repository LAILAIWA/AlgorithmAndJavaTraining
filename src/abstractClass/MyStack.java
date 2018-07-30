package abstractClass;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * 模仿JAVA Stack实现
 * @param <E>
 */
public class MyStack<E> extends Vector<E> {
    public MyStack(){
    }

    public E push(E e){
        addElement(e);
        return e;
    }

    public synchronized E pop(){
        E e;
        int len = size();

        e = peek();
        removeElementAt(--len);
        return e;
    }

    public synchronized E peek(){
        int len = size();
        if(len == 0)
            throw new EmptyStackException();
        return elementAt(len-1);
    }

    public synchronized int search(E e){
        int i = lastIndexOf(e);
        if(i >= 0)
            return size()-i;
        return -1;
    }

}
