package algorithm.question;

import algorithm.question.used.ListNode;

/**
 * 合并两个排序的链表
 *
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 *
 * 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * Related Topics
 * 分治算法
 */
public class Jz25MergeTwoLists {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        node4.next = node5;
        node5.next = node6;
        print(mergeTwoLists(node1, node4));


        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(2);
        ListNode node9 = new ListNode(3);
        ListNode node10 = new ListNode(4);
        node7.next = node8;
        node9.next = node10;
        print(mergeTwoLists(node7, node9));
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
        System.out.println();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode head = node;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            } else if (l1 == null) {
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            } else if (l2 == null) {
                node.next = l1;
                node = node.next;
                l1 = l1.next;
            } else if (l1.val > l2.val) {
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            } else {
                node.next = l1;
                node = node.next;
                l1 = l1.next;
            }
        }
        // 释放
        ListNode res = head.next;
        head.next = null;
        return res;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode node = null;
        ListNode head = null;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            } else if (l1 == null) {
                if (head == null) {
                    head = l2;
                    node = l2;
                    l2 = l2.next;
                } else {
                    node.next = l2;
                    node = node.next;
                    l2 = l2.next;
                }
            } else if (l2 == null) {
                if (head == null) {
                    head = l1;
                    node = l1;
                    l1 = l1.next;
                } else {
                    node.next = l1;
                    node = node.next;
                    l1 = l1.next;
                }
            } else if (l1.val > l2.val) {
                if (head == null) {
                    head = l2;
                    node = l2;
                    l2 = l2.next;
                } else {
                    node.next = l2;
                    node = node.next;
                    l2 = l2.next;
                }
            } else {
                if (head == null) {
                    head = l1;
                    node = l1;
                    l1 = l1.next;
                } else {
                    node.next = l1;
                    node = node.next;
                    l1 = l1.next;
                }
            }
        }
        return head;
    }
}
