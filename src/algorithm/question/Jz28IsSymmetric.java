package algorithm.question;

import algorithm.question.used.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 对称的二叉树
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。
 * 如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
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
public class Jz28IsSymmetric {

    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(2);
//        TreeNode node4 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node7 = new TreeNode(3);
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.left = node6;
//        node3.right = node7;
//        print(node1);
//        System.out.println(isSymmetric(node1));
//
//        node4.val = 0;
//        node5.val = 3;
//        node6.val = 0;
//        node7.val = 3;
//        print(node1);
//        System.out.println(isSymmetric(node1));
//
//        System.out.println(isSymmetric(null));
//
//        TreeNode node8 = new TreeNode(1);
//        System.out.println(isSymmetric(node8));
//
//        TreeNode node9 = new TreeNode(0);
//        node8.left = node9;
//        System.out.println(isSymmetric(node8));

        // [9,-42,-42,null,76,76,null,null,13,null,13] false

        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(-42);
        TreeNode node3 = new TreeNode(-42);
        TreeNode node4 = new TreeNode(76);
        TreeNode node5 = new TreeNode(76);
        TreeNode node6 = new TreeNode(13);
        TreeNode node7 = new TreeNode(13);
        node1.left = node2;
        node1.right = node3;
        node2.left = null;
        node2.right = node4;
        node3.left = node5;
        node3.right = null;
        node4.left = null;
        node4.right = node6;
        node5.left = null;
        node5.right = node7;
        print(node1);
        System.out.println(isSymmetric(node1));
    }

    /**
     * 递归实现
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        return compare(leftNode, rightNode);
    }

    private static boolean compare(TreeNode leftNode, TreeNode rightNode) {
        if (!equals(leftNode, rightNode)) {
            return false;
        } else if (leftNode == null) {
            return true;
        } else if (!equals(leftNode.right, rightNode.left)) {
            return false;
        } else if (!equals(leftNode.left, rightNode.right)) {
            return false;
        } else if (leftNode.left == null && leftNode.right == null) {
            return true;
        } else {
            return compare(leftNode.left, rightNode.right) && compare(leftNode.right, rightNode.left);
        }
    }

    /**
     * 非递归实现
     */
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        Deque<TreeNode> leftQueue = new ArrayDeque<>();
        Deque<TreeNode> rightQueue = new ArrayDeque<>();
        if (leftNode != null) {
            leftQueue.add(leftNode);
        }
        if (rightNode != null) {
            rightQueue.add(rightNode);
        }
        while (leftQueue.size() > 0 || rightQueue.size() > 0) {
            leftNode = leftQueue.poll();
            rightNode = rightQueue.poll();

            if (!equals(leftNode, rightNode)) {
                return false;
            }

            if (leftNode.left != null) {
                leftQueue.add(leftNode.left);
            }
            if (leftNode.right != null) {
                leftQueue.add(leftNode.right);
            }
            if (rightNode.right != null) {
                rightQueue.add(rightNode.right);
            }
            if (rightNode.left != null) {
                rightQueue.add(rightNode.left);
            }

            if (!equals(rightNode.right, leftNode.left)) {
                return false;
            }
            if (!equals(rightNode.left, leftNode.right)) {
                return false;
            }
        }
        return true;
    }

    private static boolean equals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root2 == null) {
            return false;
        }
        return root1.val == root2.val;
    }

    /**
     * 广度优先遍历
     */
    private static void print(TreeNode node) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (queue.size() > 0) {
            node = queue.poll();
            System.out.print(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println();
    }
}
