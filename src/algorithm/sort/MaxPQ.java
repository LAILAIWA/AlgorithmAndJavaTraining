package algorithm.sort;

public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;    //基于堆的完全二叉树
    private int N = 0; //存储于pq[1..N]中，pq[0]没有使用

    public MaxPQ(int maxN) {
        pq = (T[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T t) {
        pq[++N] = t; //新增结点
        swim(N);     //由下至上恢复堆的有序性
    }

    public T delMax() {
        T max = pq[1];   //从根节点得到最大元素
        exch(1, N--); //将其和最后一个结点交换
        pq[N + 1] = null; //防止对象游离
        sink(1);      //由上至下恢复堆的有序性
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {//循环判断条件，k>1,父结点的值比当前小
            //交换当前结点和父结点的值，以及当前游标所指下标
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {//循环判断条件，当前游标所指有子结点存在
            int j = 2 * k;
            //当子结点不是最后结点，且子结点小于右子结点，指较大的那个子结点
            if (j < N && less(j, j + 1))
                j++;
            //如果当前结点比子结点大就跳出循环
            if (!less(k, j))
                break;
            //交换，更新游标
            exch(k, j);
            k = j;
        }
    }
}
