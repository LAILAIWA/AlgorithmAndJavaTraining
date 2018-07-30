package sort;

import abstractClass.Stopwatch;

import java.util.Random;

/**
 * 插入排序
 */
public class Insertion extends SortExample{
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 1;i < N;i++){
            for(int j = i;j > 0 && less(a[j],a[j-1]);j--)
                exch(a,j,j-1);
        }
    }

    public static void main(String[] args){
        Integer[] a = RandomUtil.getRandomIndex(10000);
        Stopwatch stopwatch = new Stopwatch();
        sort(a);
        System.out.println(stopwatch.elapseTime());
        show(a);
    }
}
