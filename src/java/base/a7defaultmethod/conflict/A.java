package java.base.a7defaultmethod.conflict;

public interface A {
    default void hello(){
        System.out.println("Hello from A");
    }
}
