package categories.java.a7defaultmethod.conflict;

public interface B extends A {
    default void hello(){
        System.out.println("Hello from B");
    }
}
