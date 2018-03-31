package java8inAction.appleFilter;

import java8inAction.Apple;

public class ColorFilter implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equalsIgnoreCase("green");
    }
}
