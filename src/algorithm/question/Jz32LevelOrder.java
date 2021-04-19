package algorithm.question;

import algorithm.question.used.TreeNode;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 从上到下打印二叉树
 *
 * 1.从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 * 2.从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 3.请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 * Related Topics
 * 树
 * 广度优先搜索
 */
public class Jz32LevelOrder {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        int[] result1 = levelOrder1(node1);
        for (int i : result1) {
            System.out.print(i + ",");
        }
        System.out.println();
        List<List<Integer>> result2 = levelOrder2(node1);
        System.out.println(result2);
        List<List<Integer>> result3 = levelOrder3(node1);
        System.out.println(result3);
        TreeNode node11 = new TreeNode(1);
        TreeNode node12 = new TreeNode(2);
        TreeNode node13 = new TreeNode(3);
        TreeNode node14 = new TreeNode(4);
        TreeNode node15 = new TreeNode(5);
        node11.left = node12;
        node11.right = node13;
        node12.left = node14;
        node13.right = node15;
        List<List<Integer>> result4 = levelOrder3(node11);
        System.out.println(result4);
    }

    public static int[] levelOrder1(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        // 广度优先搜索
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        ArrayList<TreeNode> childRoots = new ArrayList<>();
        childRoots.add(root);
        count = bfs(childRoots, list, count);
        int[] result = new int[count];
        for (int i = 0; i < count ; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 广度优先搜索-递归
     */
    public static int bfs(ArrayList<TreeNode> roots, ArrayList<Integer> list, int count){
        if(roots == null || roots.size() == 0) {
            return count;
        }
        ArrayList<TreeNode> childRoots = new ArrayList<>();
        for(TreeNode temp : roots){
            list.add(temp.val);
            count++;
            // 如果有左子树，将左子树根节点放入集合中
            if(temp.left != null ) {
                childRoots.add(temp.left);
            }
            // 如过有右子树，将右子树根节点放入集合中
            if(temp.right != null) {
                childRoots.add(temp.right);
            }
        }
        // 向下递归
        return bfs(childRoots, list, count);
    }

    // 每一层打印到一行。
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 广度优先搜索
        List<List<Integer>> list = new ArrayList<>();
        ArrayList<TreeNode> childRoots = new ArrayList<>();
        childRoots.add(root);
        bfs(childRoots, list);
        return list;
    }

    public static void bfs(ArrayList<TreeNode> roots, List<List<Integer>> list){
        if(roots == null || roots.size() == 0) {
            return;
        }
        ArrayList<TreeNode> childRoots = new ArrayList<>();
        List<Integer> newList = new ArrayList<>();
        for(TreeNode temp : roots){
            newList.add(temp.val);
            // 如果有左子树，将左子树根节点放入集合中
            if(temp.left != null ) {
                childRoots.add(temp.left);
            }
            // 如过有右子树，将右子树根节点放入集合中
            if(temp.right != null) {
                childRoots.add(temp.right);
            }
        }
        list.add(newList);
        // 向下递归
        bfs(childRoots, list);
    }

    /**
     * 之字型打印
     * 层序遍历 + 双端队列（奇偶层逻辑分离）
     * 奇数层 则添加至 tmp 尾部 ，
     * 偶数层 则添加至 tmp 头部 。
     * 时间复杂度 O(N) ： N 为二叉树的节点数量，即 BFS 需循环 N 次，占用 O(N) ；双端队列的队首和队尾的添加和删除操作的时间复杂度均为 O(1) 。
     * 空间复杂度 O(N) ： 最差情况下，即当树为满二叉树时，最多有 N/2 个树节点 同时 在 deque 中，使用 O(N) 大小的额外空间。
     */
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        // 双向队列
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) {
            deque.add(root);
        }
        while(!deque.isEmpty()) {
            // 打印奇数层
            List<Integer> tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // 先左后右加入下层节点
                if(node.left != null) {
                    deque.addLast(node.left);
                }
                if(node.right != null) {
                    deque.addLast(node.right);
                }
            }
            res.add(tmp);
            if(deque.isEmpty()) {
                break; // 若为空则提前跳出
            }
            // 打印偶数层
            tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层节点
                if(node.right != null) {
                    deque.addFirst(node.right);
                }
                if(node.left != null) {
                    deque.addFirst(node.left);
                }
            }
            res.add(tmp);
        }
        return res;
    }

}
