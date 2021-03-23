package algorithm.question;

import algorithm.question.used.ListNode;

/**
 * 反转链表
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * Related Topics
 * 链表
 */
public class Jz24ReverseList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(reverseList(node1).val);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newNode = null;
        while (head.next != null) {
            ListNode lastNode = head;
            head = head.next;
            lastNode.next = newNode;
            newNode = lastNode;
            // 补上最后一个
            if (head.next == null) {
                head.next = newNode;
                break;
            }
        }
        return head;
    }
}