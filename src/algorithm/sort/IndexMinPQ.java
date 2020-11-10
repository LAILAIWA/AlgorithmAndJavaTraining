package algorithm.sort;

public class IndexMinPQ<T extends Comparable<T>> {
    private int N;       //PQ中的元素数量
    private int[] pq;    //索引二叉堆，由1开始
    private int[] qp;    //逆序，qp[pq[i]] = pq[qp[i]] = i
    private T[] keys;    //有优先级之分的元素
    public IndexMinPQ(int maxN){
        keys = (T[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0;i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public boolean comtains(int k){
        return qp[k] != -1;
    }

    public int size(){
        return N;
    }

    public void insert(int k,T t){
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = t;
        swim(N);
    }

    public T min(){
        return keys[pq[1]];
    }

    public int delMin(){
        int indexOfMin = pq[1];
        exch(1,N--);
        sink(1);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        return indexOfMin;
    }

    public int minIndex(){
        return pq[1];
    }

    public void change(int k,T t){
        keys[k] = t;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k){
        int index = qp[k];
        exch(index,N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }


    private boolean less(int i, int j){
        return keys[i].compareTo(keys[j]) < 0;
    }

    private void exch(int i, int j){
        T t = keys[i];
        keys[i] = keys[j];
        keys[j] = t;
    }

    private void swim(int k){
        while (k > 1 && less(k/2,k)){//循环判断条件，k>1,父结点的值比当前小
            //交换当前结点和父结点的值，以及当前游标所指下标
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= N){//循环判断条件，当前游标所指有子结点存在
            int j = 2*k;
            //当子结点不是最后结点，且子结点小于右子结点，指较大的那个子结点
            if(j < N && less(j,j+1))
                j++;
            //如果当前结点比子结点大就跳出循环
            if(!less(k,j))
                break;
            //交换，更新游标
            exch(k,j);
            k = j;
        }
    }
}
