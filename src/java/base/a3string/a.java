package java.base.a3string;

/**
 * @program: datastructure
 * @description: 字符串测试
 * @author: 来建培
 * @create: 2018-12-22
 */
public class a {
    public static void main(String[] args) {
        String s1 = new String("a") + new String("b");
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);

        String s3 = new String("c") + new String("d");
        String s4 = "cd";
        s3.intern();
        System.out.println(s3 == s4);
        String s5 = "e" + "f";
        String s6 = "ef";
        s5.intern();
        System.out.println(s5 == s6);
    }
}
