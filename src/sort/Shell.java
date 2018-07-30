package sort;

import abstractClass.Stopwatch;

import java.io.UnsupportedEncodingException;

public class Shell extends SortExample {
    public static void sort(Comparable[] a){
        int N = a.length;
        int H = 1;
        while (H < N/3)
            H = 3*H + 1;
        while (H >= 1){
            for(int i = H;i < N;i++){//将a[i]插入到a[i-H],a[i-2H]...之中
                for(int j = i; j >=H && less(a[j],a[j-H]);j-=H)
                    exch(a,j,j-H);
            }
            H = H/3;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        int data = 3;
        String binaryStr = java.lang.Integer.toBinaryString(data);
        System.out.println("the result is : " + binaryStr);
        byte results[] = binaryStr.getBytes("utf8");
        for(int i = 0;i < results.length ; i++){
            System.out.println("the " + i +  " result is : " + results[i]);//"1"的ascii码是49。
        }
        Integer[] a = RandomUtil.getRandomIndex(10000);
        Stopwatch stopwatch = new Stopwatch();
        sort(a);
        System.out.println(stopwatch.elapseTime());
        show(a);
    }
}
