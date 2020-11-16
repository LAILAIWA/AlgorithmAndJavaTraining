package algorithm.question;

import java.util.HashMap;
import java.util.Map;


public class Q1SumOfTwoNum {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * <p>
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * Related Topics
     * 数组
     * 哈希表
     */
    public static int[] twoSum(int[] nums, int target) {
        //超过时间限制
//        int[] result = new int[2];
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//                if (entry.getValue() + nums[i] == target) {
//                    result[0] = entry.getKey();
//                    result[1] = i;
//                }
//            }
//            map.put(i, nums[i]);
//        }
//        return result;
        // 查找map中元素值比较耗时O(n)，可以利用哈希表特性优化查询时间为O(1)
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temp = new int[4];
        temp[0] = 2;
        temp[1] = 7;
        temp[2] = 11;
        temp[3] = 15;
        int[] result = twoSum(temp, 9);
        System.out.println(result[0] + " " + result[1]);
    }
}
