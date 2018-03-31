package java8inAction.appleFilter;

import java8inAction.Apple;

public class WeightFilter implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
//        if (apple.getWeight()>=500){
//            return true;
//        }
//        return false;
        return apple.getWeight() >= 500;
    }
}
