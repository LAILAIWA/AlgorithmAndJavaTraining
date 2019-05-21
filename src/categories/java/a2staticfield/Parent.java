package categories.java.a2staticfield;

/**
 * @program: datastructure
 * @description: 父类
 * @author: 来建培
 * @create: 2019-01-09
 */
public class Parent {
    static Print printStatic = new Print("父类静态变量");
    private Print printInstance = new Print("父类实例变量");

    static {
        new Print("父类静态域");
    }

    public Parent() {
        new Print("父类构造器");
    }
}
