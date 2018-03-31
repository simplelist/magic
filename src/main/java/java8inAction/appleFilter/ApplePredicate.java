package java8inAction.appleFilter;

import java8inAction.Apple;

/**
 * 行为参数化
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
