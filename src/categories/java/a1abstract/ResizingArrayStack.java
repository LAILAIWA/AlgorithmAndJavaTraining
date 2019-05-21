package categories.java.a1abstract;

import java.util.Iterator;

/**
 * @program: datastructure
 * @description: 下压栈LIFO, 动态调整数组大小
 * @author: 来建培
 * @create: 2018-08-14
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];//栈元素
    private int N = 0;//元素数量

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        //将栈移动到一个大小为max的新数组
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = items[i];
        items = temp;
    }

    public void push(Item item) {
        //将元素加到栈顶
        if (N == items.length)
            resize(2 * items.length);
        items[N++] = item;
    }

    public Item pop() {
        //从栈顶删除元素
        Item item = items[--N];
        items[N] = null;
        if (N > 0 && N == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        //支持后进先出的迭代
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return items[--i];
        }

        public void remove() {
        }
    }
}
