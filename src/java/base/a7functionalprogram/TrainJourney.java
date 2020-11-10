package java.base.a7functionalprogram;

/**
 * 单向链表实现对火车旅行建模
 */
public class TrainJourney {
    public int price;//当前路途段价格
    public TrainJourney onward;//下一段路途
    public TrainJourney(int p, TrainJourney t){
        price = p;
        onward = t;
    }

    /**
     * 将代表X到Y和Y到Z两段路途串接起来
     * @param a 路途段a
     * @param b 路途段b
     * @return 新的路途段
     */
    static TrainJourney link(TrainJourney a, TrainJourney b){
        //a为空则直接返回b
        if(a == null) return b;
        TrainJourney t = a;
        while (t.onward != null){
            //当a的下个路途段不为空时，将t引用指向下个路途段
            t = t.onward;
        }
        //将a路途段链表的最后一节指向b路途段
        t.onward = b;
        return a;
    }

    /**
     * 递归函数式的将代表X到Y和Y到Z两段路途串接起来
     * @param a 路途段a
     * @param b 路途段b
     * @return 新的路途段
     */
    static TrainJourney append(TrainJourney a, TrainJourney b){
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }
}
