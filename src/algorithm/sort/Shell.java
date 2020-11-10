package algorithm.sort;

import java.base.a1abstract.Stopwatch;

import java.io.UnsupportedEncodingException;

/**
 * 希尔排序
 */
public class Shell extends SortExample {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int H = 1;
        while (H < N / 3)
            H = 3 * H + 1;
        while (H >= 1) {
            for (int i = H; i < N; i++) {//将a[i]插入到a[i-H],Child[i-2H]...之中
                for (int j = i; j >= H && less(a[j], a[j - H]); j -= H)
                    exch(a, j, j - H);
            }
            H = H / 3;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

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
