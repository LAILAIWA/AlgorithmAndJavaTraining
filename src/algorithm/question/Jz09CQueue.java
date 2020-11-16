package algorithm.question;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * <p>
 * <p>
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * <p>
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * <p>
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 * Related Topics
 * 栈
 * 设计
 */
public class Jz09CQueue {

    /**
     * 复杂度分析
     * <p>
     * 时间复杂度：对于插入和删除操作，时间复杂度均为 O(1)。
     * <p>
     * 插入不多说，对于删除操作，虽然看起来是 O(n) 的时间复杂度，
     * 但是仔细考虑下每个元素只会「至多被插入和弹出 stack2 一次」，
     * 因此均摊下来每个元素被删除的时间复杂度仍为 O(1)。
     * <p>
     * 空间复杂度：O(n)。需要使用两个栈存储已有的元素。
     */
    public static void main(String[] args) {
        Jz09CQueue queue = new Jz09CQueue();
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
    }

    private Stack<Integer> stackA, stackB;

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */
    public Jz09CQueue() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }


    /**
     * 在队列尾部插入整数
     */
    public void appendTail(int value) {
        stackA.push(value);
    }

    /**
     * 在队列头部删除整数
     */
    public int deleteHead() {
        if (stackB.empty()) {
            if (stackA.empty()) {
                return -1;
            }
            while (!stackA.empty()) {
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }
}
