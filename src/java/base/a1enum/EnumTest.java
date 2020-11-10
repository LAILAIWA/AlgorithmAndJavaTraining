package java.base.a1enum;


import static java.base.a1enum.EnumTest.Type.A;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-04
 */
public class EnumTest {
    enum Type { A,B,C,D }
    static String test(Type a){
        switch (a){
            case A :
                return "is A";
            case B :
                return "is B";
            case C :
                return "is C";
            case D :
                return "is D";
            default :
                throw new AssertionError("unknown type" + a);

        }
    }

    public static void main(String[] args){
        System.out.println(test(A));
        System.out.println(test(Type.B));
        System.out.println(test(Type.C));
        System.out.println(test(Type.D));
    }
}
