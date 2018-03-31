package java8inAction;

import java8inAction.appleFilter.ApplePredicate;
import java8inAction.appleFilter.WeightFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class AppleFilterTest {
    List<Apple> apples = new ArrayList<>();

    private static List<Apple> filterApple(List<Apple> list, ApplePredicate predicate) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Before
    public void before() {
        apples.add(new Apple(100, "green"));
        apples.add(new Apple(200, "red"));
        apples.add(new Apple(300, "green"));
        apples.add(new Apple(600, "red"));
        apples.add(new Apple(500, "green"));
        apples.add(new Apple(100, "yellow"));
    }

    @Test
    public void test() {
//        ApplePredicate predicate=new ColorFilter();
        ApplePredicate predicate = new WeightFilter();
        List<Apple> appleList = filterApple(this.apples, predicate);
        for (Apple apple : appleList) {
            System.out.println(apple);
        }
    }

    @Test
    public void test匿名类() {
        List<Apple> appleList = filterApple(this.apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equalsIgnoreCase("red") && apple.getWeight() >= 500;
            }
        });
        for (Apple apple : appleList) {
            System.out.println(apple);
        }
    }

    /**
     * 初识 Lambda
     */
    @Test
    public void testLambda() {
        List<Apple> appleList = filterApple(this.apples, apple -> apple.getColor().equalsIgnoreCase("red") && apple.getWeight() >= 500);
        for (Apple apple : appleList) {
            System.out.println(apple);
        }
    }

    /**
     * java.util.function
     */


    /**
     * 流
     */
    @Test
    public void testStream() {
        apples.stream().filter(apple -> apple.getWeight() >= 500 && apple.getColor().equalsIgnoreCase("red")).forEach(s -> System.out.println(s));
    }


    /**
     * 过滤
     */
    @Test
    public void testFilter() {
        System.out.println(apples.size());
        apples.stream().filter(apple -> apple.getColor().equalsIgnoreCase("red")).forEach(System.out::println);
    }

    /**
     * 对流中每一个元素应用函数
     */
    @Test
    public void testMap() {
        apples.stream().map(apple -> {
            apple.setWeight(apple.getWeight() * 10);
            return apple;
        }).forEach(System.out::println);
        System.out.println("--------------");
        apples.stream().forEach(apple -> {
                    apple.setWeight(apple.getWeight() * 10);
                    System.out.println(apple);
                }
        );

    }

    /**
     * 查找和匹配
     */
    @Test
    public void testFind() {
        System.out.println(apples.stream().findFirst().get());
        System.out.println(apples.stream().allMatch(apple -> apple.getWeight() >= 100));
    }

    /**
     * 计算
     */
    @Test
    public void testReduce() {
        int i = apples.stream().map(d -> d.getWeight()).reduce(0, (a, b) -> a + b);
        System.out.println(i);

        System.out.println(apples.stream().count());
    }

    /**
     * 获取指定结果
     */
    @Test
    public void testMapToInt() {
        System.out.println(apples.stream().mapToInt(Apple::getWeight).sum());
        System.out.println(apples.stream().mapToInt(Apple::getWeight).count());
        System.out.println(apples.stream().mapToInt(Apple::getWeight).average());

        System.out.println(IntStream.rangeClosed(1, 888).sum());
    }

    /**
     * 收集
     */
    @Test
    public void testCollect() {
        System.out.println(apples.stream().collect(summarizingInt(Apple::getWeight)).getMax());
        System.out.println(apples.stream().map(apple -> apple.getColor()).collect(joining(",")));
    }

    /**
     * 分组
     */
    @Test
    public void testGroupingCount() {
        Map<Integer, Long> collect = apples.parallelStream().collect(Collectors.groupingBy(Apple::getWeight, counting()));
        System.out.println(collect);
    }

    /**
     * 二级分组
     */
    @Test
    public void testGrouping() {
        Map<Integer, List<Apple>> collect = apples.parallelStream().collect(groupingBy(Apple::getWeight));
        System.out.println(collect);
        Map<Integer, Map<String, List<Apple>>> collect2 = apples.parallelStream().collect(groupingBy(Apple::getWeight, groupingBy(Apple::getColor)));
        System.out.println(collect2);
    }

    @Test
    public void testPartitioning() {
        //按照是否满足条件,分为 true/false 两个区
        Map<Boolean, List<Apple>> collect = apples.parallelStream().collect(Collectors.partitioningBy(apple -> apple.getWeight() >= 500));
        System.out.println(collect);
    }
}
