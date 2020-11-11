package algorithm.question;

import java.util.HashMap;
import java.util.Map;

public class Jz03FindRepeatNumber {
    /**
     * 找出数组中重复的数字。
     *
     *
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     * 请找出数组中任意一个重复的数字。
     *
     * 示例 1：
     *
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     *  
     *
     * 限制：
     *
     * 2 <= n <= 100000
     */
    public static int findRepeatNumber(int[] nums) {
        //Map<Integer, Integer> map = new HashMap<>();
        //for(int i = 0;i < nums.length;i++){
        //    if(map.containsKey(nums[i])){
        //        return nums[i];
        //    } else {
        //        map.put(nums[i], 1);
        //    }
        //}
        //return -1;
        int temp;
        // 遍历数组，将不在元素位置（值等于下标）的元素归位，若命中位置元素表示重复
        for(int i=0;i<nums.length;i++){
            while (nums[i]!=i){
                // 命中重复
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                // 否则交换
                temp=nums[i];
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
    }

}
