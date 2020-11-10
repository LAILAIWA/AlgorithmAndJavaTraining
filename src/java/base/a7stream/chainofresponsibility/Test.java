package java.base.a7stream.chainofresponsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Test {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        //将两个处理链接起来
        p1.setSuccessor(p2);

        String result = p1.handle("Aren`t labdas really sexy?!!");
        System.out.println(result);//From Raoul, Mario, and Alan: Aren`t lambdas really sexy?!!

        //通过Lambda表达式
        UnaryOperator<String> headerTextProcessing = (String input) -> "From Raoul, Mario, and Alan: " + input;
        UnaryOperator<String> spellCheckerProcessing = (String input) -> input.replaceAll("labda","lambda");
        //将两个方法结合起来
        Function<String, String> pipeline = headerTextProcessing.andThen(spellCheckerProcessing);
        String result1 = pipeline.apply("Aren`t labdas really sexy?!!");
        System.out.println(result1);//From Raoul, Mario, and Alan: Aren`t lambdas really sexy?!!
    }
}
