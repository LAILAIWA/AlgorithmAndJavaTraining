package algorithm.question;

/**
 * 矩阵中的路径
 * <p>
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * <p>
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * <p>
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 * <p>
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * <p>
 * Related Topics
 * 深度优先搜索
 *
 * @author enix
 * @date 2020/11/17
 */
public class Jz12Exist {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word));
        char[][] board1 = {{'a', 'b'}, {'c', 'd'}};
        String word1 = "abcd";
        System.out.println(exist(board1, word1));
    }


    /**
     * 时间复杂度 O(3^K * M * N) ：
     *      最差情况下，需要遍历矩阵中长度为 K 字符串的所有方案，时间复杂度为 O(3^K)；
     *      矩阵中共有 MN 个起点，时间复杂度为 O(MN) 。
     *      方案数计算： 设字符串长度为 K ，搜索中每个字符有上、下、左、右四个方向可以选择，舍弃回头（上个字符）的方向，剩下 3 种选择，因此方案数的复杂度为 O(3^K) 。
     * 空间复杂度 O(K) ：
     *      搜索过程中的递归深度不超过 K ，因此系统因函数调用累计使用的栈空间占用 O(K)
     *      （因为函数返回后，系统调用的栈空间会释放）。
     *      最坏情况下 K = MN ，递归深度为 MN ，此时系统栈使用 O(MN) 的额外空间。
     */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 深度优先搜索
     * @param board 二维矩阵
     * @param word 单词
     * @param i 横坐标
     * @param j 纵坐标
     * @param k 单词坐标
     * @return 是否命中
     */
    public static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        // 未命中：横纵坐标越界，矩阵对应位置元素不等于单词对应位置元素
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        // 单词遍历结束，全部命中
        if (k == word.length - 1) {
            return true;
        }

        // 标记表示已访问过
        board[i][j] = '\0';
        // 递归访问相邻矩阵元素
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}