package categories.java.a1abstract;

import java.util.Iterator;
import java.util.Scanner;

/**
 * @program: datastructure
 * @description: 先进先出队列
 * @author: 来建培
 * @create: 2018-08-16
 */
public class MyQueue<Item> implements Iterable<Item> {
    private Node first;//指向最早添加的结点的游标
    private Node last;//指向最近添加的结点的游标
    private int N;//队列中元素的数量
    private class Node{//定义结点的嵌套类
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){//向表尾添加元素
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue(){//从表头删除元素
        Item item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
        N--;
        return item;
    }

    //iterator


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    //测试用例: to be or not to - be - - that - - - is *
    //结果: to be or not to be (2 left on queue)
    public static void main(String[] args){
        //创建一个队列并操作字符串入列或出列
        MyQueue<String> queue = new MyQueue<String>();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String item = input.next();
            if(!item.equals("-") && !item.equals("*"))
                queue.enqueue(item);
            else if(item.equals("-"))
                System.out.print(queue.dequeue() + " ");
            else if(item.equals("*")){
                System.out.println("(" + queue.size() + " left on queue)");
                break;
            }
        }
    }
}
