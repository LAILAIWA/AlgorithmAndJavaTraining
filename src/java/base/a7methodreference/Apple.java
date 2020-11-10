package java.base.a7methodreference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Apple {
    private Integer weight;
    private String color;


    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();

        apples.add(new Apple(160,"green"));
        apples.add(new Apple(120,"red"));
        apples.add(new Apple(170,"green"));

        //Lambda表达式
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //方法引用
        apples.sort(Comparator.comparing(Apple::getWeight));
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        return "weight: " + weight + " color: " + color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
