package categories.java.a1innerClass;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-09
 */
public class Outer {
    private int outNum;

    public Outer(int outNum) {
        this.outNum = outNum;
    }

    //    public class Inner{
//        private int num;
//        private static int id;//编译Error
//        private final static int nextId1;//编译Error
//        private final static int nextId2 = 1;
//
//        public static void method(){ //编译Error
//        }
//    }
    public void start(int outNum) {
        class Inner {
            public void action() {
                System.out.println("outNum: " + outNum);
            }
        }
        Inner inner = new Inner();
        inner.action();
    }
}
