package java.base.a7defaultmethod.conflict;

public interface BB {
    default void hello(){
        System.out.println("Hello from BB");
    }
}
