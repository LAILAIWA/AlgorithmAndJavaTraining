package algorithm.question;

import algorithm.question.used.TreeNode;

import java.util.Stack;

/**
 * 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 * Related Topics
 * 树
 *
 */
public class Jz26IsSubStructure {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
        node4.left = node5;

        System.out.println(isSubStructure(node1, node4));

        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        TreeNode node10 = new TreeNode(2);
        node6.left = node7;
        node6.right = node8;
        node7.left = node9;
        node7.right = node10;

        TreeNode node11 = new TreeNode(4);
        TreeNode node12 = new TreeNode(1);
        node11.left = node12;

        System.out.println(isSubStructure(node6, node11));
    }

    /**
     * 时间复杂度 O(MN) ：
     *     其中 M,N 分别为树 A 和 树 B 的节点数量；
     *     先序遍历树 A 占用 O(M) ，每次调用 recur(A, B) 判断占用 O(N) 。
     * 空间复杂度 O(M) ：
     *     当树 A 和树 B 都退化为链表时，递归调用深度最大。
     *     当 M≤N 时，遍历树 A 与递归判断的总递归深度为 M ；
     *     当 M>N 时，最差情况为遍历至树 A 叶子节点，此时总递归深度为 M。
     *
     */
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    /**
     * 判断树 A 中当前节点为根节点的子树是否包含树 B
     */
    private static boolean recur(TreeNode A, TreeNode B) {
        // 当节点 B 为空：说明树 B 已匹配完成（越过叶子节点），因此返回 true；
        if(B == null) {
            return true;
        }
        // 当节点 A 为空：说明已经越过树 A 叶子节点，即匹配失败，返回 false；
        // 当节点 A 和 B 的值不同：说明匹配失败，返回 false ；
        if(A == null || A.val != B.val) {
            return false;
        }
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

}
