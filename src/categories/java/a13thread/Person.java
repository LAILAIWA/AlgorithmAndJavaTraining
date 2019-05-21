package categories.java.a13thread;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-03-30
 */
public class Person implements Comparable<Person>{
    private String name;
    private int age;

    @Override
    public int compareTo(Person o) {
        int diff = Integer.compare(this.age,o.getAge());
        return diff != 0 ? diff : this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "name: " + name + " age: " + age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void printName(){
        System.out.println("printName start");
        System.out.println("printName: " + name);
        try {
            System.out.println("printName execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("printName end");
    }

    public synchronized void printNameTwo(){
        System.out.println("printNameTwo start");
        System.out.println("printNameTwo: " + name + " " + name);
        try {
            System.out.println("printNameTwo execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("printNameTwo end");
    }

    public static synchronized void printStatic(){
        System.out.println("printStatic: ");
        try {
            System.out.println("printStatic execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("printStatic end");
    }

    public static synchronized void printStaticTwo(){
        System.out.println("printStaticTwo: ");
        try {
            System.out.println("printStaticTwo execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("printStaticTwo end");
    }

    public static void main(String[] args) {
        Person person1 = new Person("Tom");
        Person person2 = new Person("Lee");


        new Thread(new Runnable() {
            @Override
            public void run() {
                person1.printName();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person2.printName();
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                person1.printStatic();
//            }
//        }).start();
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                person2.printStatic();
//            }
//        }).start();

    }
}
