package algorithm.question;

import algorithm.question.used.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 重建二叉树
 */
public class Jz07BuildTree {
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     *
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7] 父 -> 左 -> 右
     * 中序遍历 inorder = [9,3,15,20,7] 左 -> 父 -> 右
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     *
     * 限制：
     *
     * 0 <= 节点个数 <= 5000
     *
     *
     *
     * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     *
     * Related Topics
     * 树
     * 递归
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        // 遍历获取每个元素在中序遍历的位置
        Map<Integer, Integer> indexMap = new HashMap<>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        // 递归
        return buildTree(preorder, 0, length - 1, 0, length - 1, indexMap);
    }

    /**
     * 递归运算
     *
     * 时间复杂度：O(n)。对于每个节点都有创建过程以及根据左右子树重建过程。
     * 空间复杂度：O(n)。存储整棵树的开销。
     *
     * @param preorder 前序遍历数组
     * @param preorderStart 前序遍历开始下标
     * @param preorderEnd 前序遍历结束下标
     * @param inorderStart 中序遍历开始下标
     * @param inorderEnd 中序遍历结束下标
     * @param indexMap 中序遍历坐标
     * @return 二叉树
     */
    public static TreeNode buildTree(int[] preorder,
                                     int preorderStart,
                                     int preorderEnd,
                                     int inorderStart,
                                     int inorderEnd,
                                     Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        // 先创建当前二叉树根节点
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        if (preorderStart == preorderEnd) {
            // 如果已经遍历完毕，直接返回二叉树
            return root;
        } else {
            // 先获取根节点在中序遍历坐标
            int rootIndex = indexMap.get(rootVal);
            // 二叉树左子树一定在中序遍历数组中根节点前面，右子树在根节点后面
            // 分别获得左子树和右子树节点数目
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            // 递归构建左子树和右子树，分别更新为当前子树在前序和中序中的起点和终点
            TreeNode leftSubtree = buildTree(preorder,
                    preorderStart + 1,
                    preorderStart + leftNodes,
                    inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder,
                    preorderEnd - rightNodes + 1,
                    preorderEnd, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }

    public static void main(String[] args){
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode node = buildTree(preorder, inorder);
        printFromTopToBottom(node);
    }

    /**
     * 按每行打印二叉树
     */
    public static void printFromTopToBottom(TreeNode root){
        if(root==null){
            return ;
        }
        int depth = depth(root);
        for (int i = 1; i <= depth; ++i) {
            levelOrder(root, i);
        }
    }

    /**
     * 层序遍历二叉树递归实现
     */
    private static int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        int l = depth(root.left);
        int r = depth(root.right);
        if(l > r){
            return l + 1;
        }else{
            return r + 1;
        }
    }

    private static void levelOrder(TreeNode node, int level) {
        if(node == null || level < 1){
            return ;
        }
        if(level == 1){
            System.out.print(node.val + " ");
            return ;
        }
        // 左子树
        levelOrder(node.left, level-1);
        // 右子树
        levelOrder(node.right, level-1);
    }
}
