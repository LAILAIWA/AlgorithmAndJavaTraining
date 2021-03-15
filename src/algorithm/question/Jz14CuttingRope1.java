package algorithm.question;

/**
 * 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 *
 * Related Topics
 * 数学
 * 动态规划
 */
public class Jz14CuttingRope1 {

    public static void main(String[] args) {
        System.out.println(cuttingRope(2));
        System.out.println(cuttingRope(10));
    }

    /**
     * 求长度为n的绳子剪掉后的最大乘积，可以从前面比n小的绳子转移而来
     *
     * 用一个dp数组记录从0到n长度的绳子剪掉后的最大乘积，也就是dp[i]表示长度为i的绳子剪成m段后的最大乘积，初始化dp[2] = 1
     *
     * 我们先把绳子剪掉第一段（长度为j），如果只剪掉长度为1，对最后的乘积无任何增益，所以从长度为2开始剪
     *
     * 剪了第一段后，剩下(i - j)长度可以剪也可以不剪。
     * 如果不剪的话长度乘积即为j * (i - j)；
     * 如果剪的话长度乘积即为j * dp[i - j]。
     * 取两者最大值max(j * (i - j), j * dp[i - j])
     *
     * 第一段长度j可以取的区间为[2,i)，对所有j不同的情况取最大值，因此最终dp[i]的转移方程为
     *
     * dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
     *
     * 最后返回dp[n]即可
     *
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public static int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i < n + 1; i++){
            for(int j = 2; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 贪心
     *
     * 核心思路是：尽可能把绳子分成长度为3的小段，这样乘积最大
     *
     * 步骤如下：
     *
     * 如果 n == 2，返回1，如果 n == 3，返回2，两个可以合并成n小于4的时候返回n - 1
     * 如果 n == 4，返回4
     * 如果 n > 4，分成尽可能多的长度为3的小段，每次循环长度n减去3，乘积res乘以3；最后返回时乘以小于等于4的最后一小段
     * 以上2和3可以合并
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int cuttingRope1(int n) {
        if(n < 4){
            return n - 1;
        }
        int res = 1;
        while(n > 4){
            res *= 3;
            n -= 3;
        }
        return res * n;
    }

}
