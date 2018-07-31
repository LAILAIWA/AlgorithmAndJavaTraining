package sort;

import abstractClass.Stopwatch;

public class Set extends SortExample {
    public static void sort(Comparable[] c){
        Comparable[] a = new Comparable[c.length+1];
        int N = c.length;
        for (int i = 0;i < N;i++)
            a[i+1] = c[i];
        for(int k = N/2; k >= 1; k--)
            sink(a,k,N);
        while (N > 1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }
    private static void swim(Comparable[] a,int k){
        while (k > 1 && less(k/2,k)){//循环判断条件，k>1,父结点的值比当前小
            //交换当前结点和父结点的值，以及当前游标所指下标
            exch(a,k/2,k);
            k = k/2;
        }
    }

    private static void sink(Comparable[] a,int k,int N){
        while (2*k <= N){//循环判断条件，当前游标所指有子结点存在
            int j = 2*k;
            //当子结点不是最后结点，且子结点小于右子结点，指较大的那个子结点
            if(j < N && less(j,j+1))
                j++;
            //如果当前结点比子结点大就跳出循环
            if(!less(k,j))
                break;
            //交换，更新游标
            exch(a,k,j);
            k = j;
        }
    }

    public static void exch(Comparable[] a,int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        Integer[] a = RandomUtil.getRandomIndex(10000);
        Stopwatch stopwatch = new Stopwatch();
        sort(a);
        System.out.println(stopwatch.elapseTime());
        show(a);
    }
}
