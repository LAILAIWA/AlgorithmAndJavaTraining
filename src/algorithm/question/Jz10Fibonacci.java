package algorithm.question;

/**
 * 斐波那契数列
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 *
 * 0 <= n <= 100
 * Related Topics
 * 递归
 */
public class Jz10Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(5));
        // 807526948
        System.out.println(fib(48));
    }

    /**
     * 时间复杂度：o(N)
     * 空间复杂度：o(1)
     */
    public static int fib(int n) {
//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        } else if (n > 1000000007) {
//            n = n - 1000000007;
//        }
//        return fib(n - 1) + fib(n - 2);
        //Time Limit Exceeded
        int fn2 = 1, fn1 = 0, res = fn1;
        for (int i = 0; i < n; i++) {
            res = fn1 + fn2;
            if (res >= 1000000007) {
                res -= 1000000007;
            }
            fn2 = fn1;
            fn1 = res;
        }
        return res;
    }
}
