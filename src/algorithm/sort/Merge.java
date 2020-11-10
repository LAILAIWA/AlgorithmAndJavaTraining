package algorithm.sort;

import java.base.a1abstract.Stopwatch;

/**
 * 归并排序
 */
public class Merge extends SortExample {
    private static Comparable[] aux; //归并所需的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];//一次性分配空间
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {//将数组a排序，递归排序，sort的作用为以正确的顺序调用merge方法
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void sortBottomUp(Comparable[] a) {//自底向上的归并排序，进行lgN次两两归并
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) //sz-子数组大小
            for (int lo = 0; lo < N - sz; lo += sz + sz) //子数组索引
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));

    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {//原地归并
        //将a[lo,mid]与a[mid+1,hi]归并，两个数组是有序的
        //先将所有元素复制到aux[]中，再归并到a[]中
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {//用i,j指针分别游离于两数组
            if (i > mid) a[k] = aux[j++]; //左边下标已结束
            else if (j > hi) a[k] = aux[i++];//右边下标已结束
            else if (less(aux[j], aux[i])) a[k] = aux[j++];//两数组的指针比较当前大小，前者较小
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        double totalTime = 0;
        for (int i = 0; i < 100; i++) {
            Integer[] a = RandomUtil.getRandomIndex(10000);
            Stopwatch stopwatch = new Stopwatch();
            sort(a);
            //System.out.println(stopwatch.elapseTime());
            //show(Child);
            totalTime += stopwatch.elapseTime();
        }
        System.out.println("100次平均耗时：" + (totalTime / 100));
    }
}
