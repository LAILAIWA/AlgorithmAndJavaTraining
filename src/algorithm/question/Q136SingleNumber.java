package algorithm.question;

import java.util.*;

/**
 * 只出现一次的数字
 */
public class Q136SingleNumber {

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.print(singleNumber(nums));
    }

    /**
     * 《只出现一次的数字》
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }


    /**
     * 1.列表操作
     * 时间复杂度：O(n^2)
     * 我们遍历nums花费O(n)的时间。我们还要在列表中遍历判断是否存在这个数字，花费O(n)的时间，所以总循环时间为 O(n^2)
     * 空间复杂度：O(n)。
     * 我们需要一个大小为 n 的列表保存所有的nums中元素。
     */
    public int singleNumber1(int[] nums) {
        List<Integer> list = new LinkedList<>();
        //遍历nums中的每一个元素
        for (int num : nums) {
            if (!list.contains(num)) {
                //如果某个nums中的数字是新出现的，则将它添加到列表中
                list.add(num);
            } else {
                //如果某个数字已经在列表中，删除它
                list.remove(num);
            }
        }
        return list.get(0);
    }

    /**
     * 2.哈希表
     * 时间复杂度： O(n⋅1)=O(n)。
     * for循环的时间复杂度是O(n)的，pop操作时间复杂度为O(1)O(1)
     * 空间复杂度： O(n)。
     * hash_table需要的空间与nums中元素个数相等。
     */
    public int singleNumber2(int[] nums) {
        //哈希表存放当前元素
        Map<Integer, Object> map = new HashMap<>();
        //遍历nums中的每一个元素
        for (int num : nums) {
            //查找hash_table中是否有当前元素的键
            if (!map.containsKey(num)) {
                //如果没有，将当前元素作为键插入hash_table
                map.put(num, null);
            } else {
                //如果有，删除它
                map.remove(num);
            }
        }
        return map.keySet().iterator().next();
    }

    /**
     * 3.数学
     * 2∗(a+b+c)−(a+a+b+b+c)=c
     * 时间复杂度：O(n + n) = O(n)。
     * sum会调用next将nums中的元素遍历一遍。我们可以把上述代码看成 sum(list(i, for i in nums)) ，这意味着时间复杂度为O(n)，因为nums中的元素个数是n个。
     * 空间复杂度：O(n + n) = O(n)。
     * set需要的空间跟nums中元素个数相等。
     */
    public int singleNumber3(int[] nums) {
        Integer[] numArray = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        //(a+a+b+b+c)
        int sum = 0;
        for (Integer integer : numArray) {
            sum += integer;
        }
        //(a+b+c)
        Set<Integer> set = new HashSet<>(Arrays.asList(numArray));
        int setSum = set.stream().mapToInt(Integer::intValue).sum();
        return 2 * setSum - sum;
    }

    /**
     * 4.位操作-异或运算
     * a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
     * 时间复杂度： O(n) 。
     * 我们只需要将nums中的元素遍历一遍，所以时间复杂度就是nums中的元素个数。
     * 空间复杂度：O(1) 。
     */
    public int singleNumber4(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
