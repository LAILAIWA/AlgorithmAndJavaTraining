package java.base.a1abstract;

import java.util.Arrays;

public class StaticSetOfInts {
    private int[] a;
    public StaticSetOfInts(int[] keys){
        //拷贝为新的数组
        a = new int[keys.length];
        for(int i = 0;i < keys.length;i++)
            a[i] = keys[i];
        Arrays.sort(a);//对数组排序，由小到大
    }

    public boolean contains(int key){
        return rank(key) != -1;
    }

    public int rank(int key){
        //二分查找
        int lo = 0;
        int hi = a.length-1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(key < a[mid])
                hi = mid-1;
            else if(key > a[mid])
                lo = mid+1;
            else
                return mid;
        }
        return -1;
    }
}
