package java.base.a1abstract;

import java.util.Iterator;

/**
 * 用链表实现栈结构
 */
public class LinkedListStack<E> implements Iterable<E> {
    //成员内部类声明结点
    private Node first;//栈顶
    private int N;//栈内元素数量
    private class Node{
        E e;
        Node next;
    }
    public boolean isEmpty(){
        return first == null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
