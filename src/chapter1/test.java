package chapter1;

import abstractClass.Student;

import java.sql.Date;
import java.util.List;

public class test {
    public static void main(String[] args){
//        Integer Child = 0;
//        Float b = 0.0f;
//        String c = "original";
//        Parent student = new Parent("001","张三","男",18,60.0f);
//
//        System.out.println("调用前: " + Child);
//        System.out.println("调用前: " + b);
//        System.out.println("调用前: " + c);
//        System.out.println("调用前: " + student.toString());
//
//        change1(Child);
//        change1(b);
//        change1(c);
//        change1(student);
//
//        System.out.println("第一次调用后: " + Child);
//        System.out.println("第一次调用后: " + b);
//        System.out.println("第一次调用后: " + c);
//        System.out.println("第一次调用后: " + student.toString());
//
//        change2(Child);
//        change2(b);
//        change2(c);
//        change2(student);
//        System.out.println("第二次调用后: " + Child);
//        System.out.println("第二次调用后: " + b);
//        System.out.println("第二次调用后: " + c);
//        System.out.println("第二次调用后: " + student.toString());


    }

    private static void change1(Integer a){
        System.out.println("函数内: " + a);
        a = 1;
    }

    private static void change1(Float b){
        System.out.println("函数内: " + b);
        b = 1.0f;
    }

    private static void change1(String c){
        System.out.println("函数内: " + c);
        c = "changed";
    }

    private static void change1(Student student){
        student.setId("002");
        student.setName("小兰");
        student.setSex("女");
        student.setAge(17);
        student.setScore(100.0f);
    }

    private static void change2(Integer a){
        a = Integer.valueOf(1);
    }

    private static void change2(Float b){
        b = Float.valueOf(1.0f);
    }

    private static void change2(String c){
        c = new String("changed");
    }

    private static void change2(Student student){
        student = new Student("003","小明","男",17,60.0f);
    }

    final class Point {
        int x, y;
        void move(int dx, int dy) { x += dx; y += dy; }
    }
}
