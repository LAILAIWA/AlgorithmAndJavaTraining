package algorithm.question;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * 提示：
 *
 * 0 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 */
public class Jz21Exchange {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        nums = exchange(nums);
        for (int i = 0;i < nums.length;i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static int[] exchange(int[] nums) {
        int[] numsNew = new int[nums.length];
        for (int i = 0,j = 0,k = nums.length - 1;i < nums.length;i++) {
            if (nums[i] % 2 == 0) {
                // 偶数
                numsNew[k] = nums[i];
                k--;
            } else {
                numsNew[j] = nums[i];
                j++;
            }
        }
        return numsNew;
    }

    public static int[] exchange1(int[] nums) {
        int point = 0;
        for (int i = 0;i < nums.length;) {
            int num = nums[i];
            if (point + i >= nums.length) {
                return nums;
            }
            if (num % 2 == 0) {
                // 偶数，与数组末尾交换
                int temp = nums[i];
                nums[i] = nums[nums.length - 1 - point];
                nums[nums.length - 1 - point] = temp;
                point++;
            } else {
                // 奇数
                i++;
                point = 0;
            }
        }
        return nums;
    }
}
