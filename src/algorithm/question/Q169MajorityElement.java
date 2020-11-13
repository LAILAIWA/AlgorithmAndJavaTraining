package algorithm.question;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 */
public class Q169MajorityElement {

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * Related Topics
     * 位运算
     * 数组
     * 分治算法
     */
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num)) {
                counter.put(num, 1);
            } else {
                int now = counter.get(num) + 1;
                if (now > nums.length / 2) {
                    return num;
                }
                counter.put(num, now);
            }
        }
        //当数组只包含一个元素时由此返回
        return counter.keySet().iterator().next();
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.print(majorityElement(nums));
    }
}
