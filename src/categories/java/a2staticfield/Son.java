package categories.java.a2staticfield;

/**
 * @program: datastructure
 * @description: 测试类中静态部分加载顺序
 * @author: 来建培
 * @create: 2019-01-09
 */
public class Son extends Parent {
    static Print printStatic = new Print("子类静态变量");
    private Print printInstance = new Print("子类实例变量");

    static {
        new Print("子类静态域");
    }

    static Print printStatic1 = new Print("子类静态变量1");

    public Son() {
        new Print("子类构造器");
    }

    public static void main(String[] args) {
        Son son = new Son();
    }
}
