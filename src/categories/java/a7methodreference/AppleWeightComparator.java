package categories.java.a7methodreference;

import java.util.Comparator;

public class AppleWeightComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
