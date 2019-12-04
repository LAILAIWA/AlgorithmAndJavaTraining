package categories.java.a7defaultmethod.conflict;

public class E implements A,BB{
    public void hello() {
        BB.super.hello();
    }
    public static void main(String[] args) {
       new E().hello();//Hello from BB
    }
}
