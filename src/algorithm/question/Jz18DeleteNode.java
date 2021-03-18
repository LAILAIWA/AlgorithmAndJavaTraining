package algorithm.question;

import algorithm.question.used.ListNode;

/**
 * 删除链表的节点
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 *
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * 说明：
 *
 *      题目保证链表中节点的值互不相同
 *
 * Related Topics
 *
 *      链表
 *
 */
public class Jz18DeleteNode {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(1);
        ListNode listNode4 = new ListNode(9);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        print(deleteNode(listNode1, 1));
    }

    public static ListNode deleteNode(ListNode head, int val) {
        int count = 0;
        ListNode firstElement = head;
        ListNode lastElement = head;
        while (head != null) {
            if (head.val == val) {
                if (count == 0) {
                    // 当前节点是头结点
                    ListNode newHead = head.next;
                    head.next = null;
                    return newHead;
                } else if (head.next == null){
                    // 尾节点
                    lastElement.next = null;
                    return firstElement;
                } else {
                    // 中间节点
                    lastElement.next = head.next;
                    head.next = null;
                    return firstElement;
                }
            }
            lastElement = head;
            head = head.next;
            count++;
        }
        return firstElement;
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
        System.out.println();
    }
}
