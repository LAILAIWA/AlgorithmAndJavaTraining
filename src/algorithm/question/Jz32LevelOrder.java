package algorithm.question;

import algorithm.question.used.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 从上到下打印二叉树
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
//        int[] result = levelOrder(node1);
//        for (int i : result) {
//            System.out.print(i + ",");
//        }
        List<List<Integer>> result = levelOrder(node1);
        System.out.println(result);
    }

//    public static int[] levelOrder(TreeNode root) {
//        if (root == null) {
//            return new ArrayList<>();
//        }
//        // 广度优先搜索
//        ArrayList<Integer> list = new ArrayList<>();
//        int count = 0;
//        ArrayList<TreeNode> childRoots = new ArrayList<>();
//        childRoots.add(root);
//        count = bfs(childRoots, list, count);
//        int[] result = new int[count];
//        for (int i = 0; i < count ; i++) {
//            result[i] = list.get(i);
//        }
//        return result;
//    }
//
//    /**
//     * 广度优先搜索-递归
//     */
//    public static int bfs(ArrayList<TreeNode> roots, ArrayList<Integer> list, int count){
//        if(roots == null || roots.size() == 0) {
//            return count;
//        }
//        ArrayList<TreeNode> childRoots = new ArrayList<>();
//        for(TreeNode temp : roots){
//            list.add(temp.val);
//            count++;
//            // 如果有左子树，将左子树根节点放入集合中
//            if(temp.left != null ) {
//                childRoots.add(temp.left);
//            }
//            // 如过有右子树，将右子树根节点放入集合中
//            if(temp.right != null) {
//                childRoots.add(temp.right);
//            }
//        }
//        // 向下递归
//        return bfs(childRoots, list, count);
//    }

    // 每一层打印到一行。
    public static List<List<Integer>> levelOrder(TreeNode root) {
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

}
