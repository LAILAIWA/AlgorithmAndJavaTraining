package categories.java.a7lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
    private String id;
    private String name;
    private int age;
    private String sex;

    static List<Student> filter(List<Student> students, Predicate<Student> predicate){
        List<Student> result = new ArrayList();
        for(Student student : students){
            if(predicate.test(student)){
                result.add(student);
            }
        }
        return result;
    }

    //实现判断函数
    public static boolean isStudentMan(Student student){
        return "man".equals(student.getSex());
    }
    public static boolean isStudentOver18(Student student){
        return 18 < student.getAge();
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("001","张三",19,"man"));
        students.add(new Student("002","李四",17,"man"));
        students.add(new Student("003","王五",17,"man"));
        students.add(new Student("004","马六",18,"man"));
        students.add(new Student("005","兰兰",16,"woman"));
        students.add(new Student("001","花花",19,"woman"));

        Predicate<Student> conditionMan = Student::isStudentMan;
        List<Student> resultMan = filter(students,conditionMan);
        print(resultMan);
        List<Student> resultWoMan = filter(students,conditionMan.negate());
        print(resultWoMan);

        List<Student> resultOver18 = filter(students,Student::isStudentOver18);
        print(resultOver18);

        //为需求实现一堆判断函数太繁琐了，可以直接通过匿名函数或Lambda来实现
        List<Student> resultManLambda = filter(students,(Student student) -> "man".equals(student.getSex()));
        print(resultManLambda);
        List<Student> resultOver18Lambda = filter(students,(Student student) -> 18 < student.getAge());
        print(resultOver18Lambda);

        //Stream API实现筛选和转换
        List<Student> resultManLibrary = students.stream().filter((Student student) -> "man".equals(student.getSex())).collect(Collectors.toList());
        print(resultManLibrary);
    }

    public Student(String id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    static void print(List<Student> students){
        for(Student student : students){
            System.out.println(student.toString());
        }
        System.out.println("------");
    }

    @Override
    public String toString() {
        return "id: " + id + "name: " +  name + "age: " +  age + "sex: " +  sex;
    }
}
