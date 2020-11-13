package algorithm.question;

import algorithm.question.used.ListNode;
import algorithm.question.util.ListUtils;

import java.util.Arrays;
import java.util.Optional;

public class Jz06ReversePrint {
    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     *
     *
     * 示例 1：
     *
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *
     *
     * 限制：
     *
     * 0 <= 链表长度 <= 10000
     *
     * Related Topics
     * 链表
     */
    public static int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode move = head;
        while(move != null) {
            count++;
            move = move.next;
        }
        int[] list = new int[count];
        while(head != null) {
            list[count-1] = head.val;
            count--;
            head = head.next;
        }
        return list;
    }

    public static void main(String[] args){
        int[] heads = {1,3,2};
        // 初始化链表
        ListNode last = null;
        ListNode head = null;
        for(int i : heads) {
            ListNode node = new ListNode(i);
            if(last != null) {
                last.next = node;
            } else {
                head = node;
            }
            last = node;
        }
        int[] result = reversePrint(head);
        ListUtils.printList(result);
    }
}
