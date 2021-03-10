package algorithm.question;

/**
 * 二进制中1的个数
 *
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
 * 例如，把 9 表示成二进制是 1001，有 2 位是 1。
 * 因此，如果输入 9，则该函数输出 2。
 *
 *
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 *
 * 提示：
 *
 * 输入必须是长度为 32 的 二进制串 。
 *
 * 位运算
 */
public class Jz15HammingWeight {

    public static void main(String[] args) {
        int input = 11;
        System.out.println(hammingWeight1(input));
        input = 128;
        System.out.println(hammingWeight1(input));
    }

    /**
     * you need to treat n as an unsigned value
     *
     * 时间复杂度 O(log2n) ： 此算法循环内部仅有移位、与、加等基本运算，占用 O(1)；
     * 逐位判断需循环 log2n次，其中 log2n代表数字 n 最高位 1 的所在位数
     * （例如 log 2 4 = 2, log 2 16 = 4）。
     *
     * 空间复杂度 O(1) ： 变量 res 使用常数大小额外空间。
     *
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            // 无符号右移
            n = n >>> 1;
        }
        return count;
    }

    public static int hammingWeight1(int n) {
        return Integer.bitCount(n);
    }
}
