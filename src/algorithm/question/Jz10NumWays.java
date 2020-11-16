package algorithm.question;

/**
 * 青蛙跳台阶问题
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 */
public class Jz10NumWays {
    //1: 1
    //2: 11 2
    //3: 111 12 21
    //4: 1111 121 211 112
    //5: 11111 1211 2111 1121 122 212 221
    // f(n) = f(n-1) + f()
    public static void main(String[] args) {
        System.out.println(numWays(1));
        System.out.println(numWays(2));
        System.out.println(numWays(7));
        // 807526948
        System.out.println(numWays(48));
    }

    public static int numWays(int n) {
        int fn2 = 0, fn1 = 1, res = fn1;
        for (int i = 0; i < n; i ++) {
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
