package java.base.a7defaultmethod.conflict;

public class C extends D implements A,B{
    public static void main(String[] args) {
        new C().hello();
    }
}
