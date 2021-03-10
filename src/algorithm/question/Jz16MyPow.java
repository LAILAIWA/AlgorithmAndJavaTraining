package algorithm.question;

/**
 * 数值的整数次方
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * 不得使用库函数，同时不需要考虑大数问题。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = 1/(2^2) = 1/4 = 0.25
 * <p>
 * <p>
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * 递归
 */
public class Jz16MyPow {

    public static void main(String[] args) {
        double x = 2.00000;
        int n = 10;
        System.out.println(myPow(x, n));

        x = 2.10000;
        n = 3;
        System.out.println(myPow(x, n));

        x = 2.00000;
        n = -2;
        System.out.println(myPow(x, n));

        x = 0.00001;
        n = 2147483647;
        System.out.println(myPow(x, n));
    }

    /**
     * 暴力解法：循环将n个x乘起来，时间复杂度为O(n)。
     *
     * 快速幂法：（二分推导） x^n = x^(n/2) * x^(n/2)
     *
     * n为偶数：x^n = (x^2) * (n/2)
     * n为奇数：x^n = x * (x^2) * (n/2)
     *
     * 循环x = x ^ 2 操作，每次n降为 n / 2
     *
     * 时间复杂度 O(log 2 n)： 二分的时间复杂度为对数级别。
     * 空间复杂度 O(1) ： res, b 等变量占用常数大小额外空间。
     *
     * Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，
     * 因此当 n = -2147483648 时执行 n = -n 会因越界而赋值出错。
     * 解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可。
     *
     */
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
