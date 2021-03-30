package algorithm.question;

/**
 * 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * Related Topics
 * 数组
 *
 */
public class Jz29SpiralOrder {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        print(spiralOrder(matrix));
        int[][] matrix1 = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        print(spiralOrder(matrix1));
        int[][] matrix2 = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        print(spiralOrder(matrix2));
    }

    /**
     * 复杂度分析：
     * 时间复杂度 O(MN) ： M, N 分别为矩阵行数和列数。
     * 空间复杂度 O(1) ： 四个边界 l , r , t , b 使用常数大小的额外空间。
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        // 四个角
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            // left to right.
            for(int i = l; i <= r; i++) {
                res[x++] = matrix[t][i];
            }
            if(++t > b) {
                break;
            }
            // top to bottom.
            for(int i = t; i <= b; i++) {
                res[x++] = matrix[i][r];
            }
            if(l > --r) {
                break;
            }
            // right to left.
            for(int i = r; i >= l; i--) {
                res[x++] = matrix[b][i];
            }
            if(t > --b) {
                break;
            }
            // bottom to top.
            for(int i = b; i >= t; i--) {
                res[x++] = matrix[i][l];
            }
            if(++l > r) {
                break;
            }
        }
        return res;
    }

    public static void print(int[] list) {
        for(int i : list) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
