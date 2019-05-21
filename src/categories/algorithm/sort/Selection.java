package categories.algorithm.sort;

import categories.java.a1abstract.Stopwatch;

/**
 * 选择排序
 */
public class Selection extends SortExample{

    public static void sort(Comparable[] a){//将数组a按升序
        int N = a.length;//记录数组长度
        for(int i = 0;i < N;i++){//将a[i]与a[i+1...N]最小元素交换
            int min = i;
            for(int j = i+1;j < N;j++)
                if(less(a[j],a[min])) min = j;
            exch(a,i,min);
        }
    }

    public static void main(String[] args){
        double totalTime = 0;
        for(int i = 0;i < 100;i++){
            Integer[] a = RandomUtil.getRandomIndex(10000);
            Stopwatch stopwatch = new Stopwatch();
            sort(a);
            //System.out.println(stopwatch.elapseTime());
            //show(Child);
            totalTime+=stopwatch.elapseTime();
        }
        System.out.println("100次平均耗时：" + (totalTime/100));
    }
}
