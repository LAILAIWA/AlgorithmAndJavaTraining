package categories.java.a7stream.factory;

import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {
        Product p = ProductFactory.createProduct("loan");
        //通过Lambda表达式
        Product p1 = ProductFactory.createProductL("loan");

    }
}
