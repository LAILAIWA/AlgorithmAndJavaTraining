package algorithm.question;

import algorithm.question.used.ListNode;
import algorithm.question.used.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的镜像
 *
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * Related Topics
 * 树
 *
 */
public class Jz27MirrorTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
//        print(node1);
        print(mirrorTree(node1));
    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 广度优先遍历
        TreeNode head = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            root = queue.poll();
            // 左右互换
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
        }
        return head;
    }

    /**
     * 广度优先遍历
     */
    private static void print(TreeNode node) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (queue.size() > 0) {
            node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
