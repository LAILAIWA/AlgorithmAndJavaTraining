package categories.java.a7defaultmethod;

public class Test {
    public static void main(String[] args) {
        Monster m = new Monster();
        m.rotateBy(180);
        m.moveVertically(10);
        Sun sun = new Sun();
        sun.moveHorizontally(100);
        sun.rotateBy(6);
    }
}
