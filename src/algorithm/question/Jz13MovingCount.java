package algorithm.question;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class Jz13MovingCount {

    public static void main(String[] args) {
        int m = 2, n = 3, k = 1;
        System.out.println(movingCount(m, n , k));
        m = 3;
        n = 1;
        k = 0;
        System.out.println(movingCount(m, n , k));
        m = 16;
        n = 8;
        k = 4;
        System.out.println(movingCount(m, n , k));
    }

    /**
     * 深度优先搜索
     *
     * 时间复杂度：O(mn)，其中 m 为方格的行数， n 为方格的列数。
     * 一共有 O(mn) 个状态需要计算，每个状态递推计算的时间复杂度为 O(1)，所以总时间复杂度为 O(mn)。
     *
     * 空间复杂度：O(mn)，其中 m 为方格的行数，n 为方格的列数。我们需要 O(mn) 大小的结构来记录每个位置是否可达。
     *
     */
    public static int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    public static int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if(i >= m || j >= n || k < getSum(i) + getSum(j) || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(i + 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited);
    }

    private static int getSum(int a) {
        int sum = a % 10;
        int tmp = a / 10;
        while(tmp > 0) {
            sum += tmp % 10;
            tmp /= 10;
        }
        return sum;
    }
}
