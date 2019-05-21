package categories.java.a3string;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @program: datastructure
 * @description: 字符串测试
 * @author: 来建培
 * @create: 2018-12-22
 */
public class a {
    public static void main(String[] args) {

        //先常量赋值，后new新建
//        String s1 = "abc";
//        String s2 = new String("abcasdasdasd");
//        String s3 = s2.intern();
//        System.out.println("s2 == s3 : " + (s2 == s3));
//        String s3 = "abc";
//        printAddresses("s1",s1);
//        printAddresses("s2",s2);
//        printAddresses("s2.intern()",s2.intern());
//        printAddresses("s3",s3);
//        System.out.println("s1 == s2 : " + (s1 == s2));
//        System.out.println("s1 == s3 : " + (s1 == s3));

        //先new新建，后常量赋值
//        String s1 = new String("我我");
//        s1.intern();
//        System.out.println("s1 == s2 : " + (s1 == s1.intern()));
//        String s2 = "abc";
//        printAddresses("s1",s1);
//        printAddresses("s1.intern()",s1.intern());
//        printAddresses("s2",s2);
//        System.out.println("s1 == s2 : " + (s1 == s2));
        //String str1 = new StringBuilder("计算机").append("软件").toString();
//        String str1 = new String("计算机软件");
//        printAddresses("str1",str1);
//        printAddresses("str1.intern()",str1.intern());
//        String str2 = "计算机软件";
//        printAddresses("str2",str2);
//        System.out.println(str1.intern() == str1);
//
//
//        String s1 = new String("a") + new String("b");
//        s1.intern();
//        String s2 = "ab";
//        printAddresses("s1",s1);
//        printAddresses("s1.intern()",s1.intern());
//        printAddresses("s2",s2);
//        System.out.println(s1 == s2);
//
//        String s3 = new String("c") + new String("d");
//        String s4 = "cd";
//        s3.intern();
//        printAddresses("s3",s3);
//        printAddresses("s4",s4);
//        printAddresses("s3.intern()",s3.intern());
//        System.out.println(s3 == s4);
//
//        String s5 = "e" + "f";
//        String s6 = "ef";
//        s5.intern();
//        printAddresses("s5",s5);
//        printAddresses("s6",s6);
//        printAddresses("s5.intern()",s5.intern());
//        System.out.println(s5 == s6);


//        String[] strings = new String[10];
//        for(int i = 0;i < strings.length;i++){
//            strings[i] = "abc";
//            //strings[i].intern();
//        }
//
//        for(int i = 0;i < strings.length;i++){
//            printAddresses("s" + i,strings[i]);
//        }

//        String s3 = new String("1") + new String("1");
//        String s5 = s3.intern();
//        String s4 = "11";
//        System.out.println(s5 == s3);
//        System.out.println(s5 == s4);
//        System.out.println(s3 == s4);
//
//        System.out.println("======================");
//
//        String s6 = new String("go") +new String("od");
//        String s7 = s6.intern();
//        String s8 = "good";
//        System.out.println(s6 == s7);
//        System.out.println(s7 == s8);
//        System.out.println(s6 == s8);
//        printAddresses("s3",s3);
//        printAddresses("s4",s4);
//        printAddresses("s5",s5);
//        printAddresses("s6",s6);
//        printAddresses("s7",s7);
//        printAddresses("s8",s8);

//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4);

//        int Child = 1;
//        String b = "ori";
//        String s = new String("abc");
//        String s1 = "abc";
//        String s2 = new String("abc");
//        String s3 = "abc";
//        String s4 = "Child" + "bc";
//        String s5 = new String("abc");
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s1 == s4);
//        System.out.println(s2 == s5);
//        System.out.println(s1 == s1.intern());
//        System.out.println(s1 == s4.intern());
//        printAddresses("s1",s1);
//        printAddresses("s2",s2);
//        printAddresses("s3",s3);
//        printAddresses("s4",s4);
//        printAddresses("s5",s5);
//        printAddresses("s1.intern()",s1.intern());
//
//        StringBuilder sb = new StringBuilder(120);
//        sb.toString();

//        String str1 = new StringBuilder("计算机").append("软件").toString();
//        System.out.println(str1.intern() == str1);
//
//        String str2 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str2.intern() == str2);

//        String sa1 = new String("1");
//        sa1.intern();
//        String sa2 = "1";
//        printAddresses("sa1",sa1);
//        printAddresses("sa1.intern()",sa1.intern());
//        printAddresses("sa2",sa2);
//        System.out.println("sa1 == sa2 : " + (sa1 == sa2));

//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4);
//
//        String s5 = new String("1") + new String("1");
//        String s6 = "11";
//        s5.intern();
//        System.out.println(s5 == s6);
//        printAddresses("s2",s2);
//        printAddresses("s3",s3);
//        printAddresses("s4",s4);
//        printAddresses("s5",s5);
//        printAddresses("s6",s6);
    }

    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true;

    public static void printAddresses(String label, Object... objects) {
        System.out.print(label + ": 0x");
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString(last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
