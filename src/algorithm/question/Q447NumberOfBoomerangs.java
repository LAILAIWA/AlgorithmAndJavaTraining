package algorithm.question;

import java.util.HashMap;
import java.util.Map;

/**
 * 回旋镖的数量
 */
public class Q447NumberOfBoomerangs {

    /**
     * 《回旋镖的数量》
     * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     *
     * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
     *
     * 示例:
     *
     * 输入:
     * [[0,0],[1,0],[2,0]]
     *
     * 输出:
     * 2
     *
     * 解释:
     * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
     */
    public static int numberOfBoomerangs(int[][] points) {
        //HashMap存放当前点和其它点的距离
        Map<Integer,Integer> temp = new HashMap<>();
        int count = 0;
        //两次遍历，i指针选择当前点，j指针遍历其它点
        for(int i = 0; i < points.length; i++){
            //每次换点清空Map
            temp.clear();
            //遍历内层数组获取各点的坐标，分别在0,1位
            for(int j = 0; j < points.length; j++){
                if(i != j){
                    //计算距离，因为回旋镖可以更换j和k位置，所以每找到一组就*2
                    int distance = new Double(Math.pow((points[i][0] - points[j][0]),2) + Math.pow((points[i][1] - points[j][1]),2)).intValue();
                    //累加获得当前所有回旋镖数
                    count += temp.getOrDefault(distance,0) * 2;
                    //更新此距离值对应点数
                    temp.put(distance,temp.getOrDefault(distance,0) + 1);
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[][] points = {{0,0},{1,0},{2,0}};
        System.out.print(numberOfBoomerangs(points));
    }
}
