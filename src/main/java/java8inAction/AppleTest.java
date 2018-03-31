package java8inAction;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppleTest {
    List<Apple> apples = new ArrayList<>();

    private static List<Apple> filterGreenApple(List<Apple> list) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (apple.getColor().equalsIgnoreCase("green")) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterGreenApple(List<Apple> list, String color) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (apple.getColor().equalsIgnoreCase(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterWeightApple(List<Apple> list, int weight) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (apple.getWeight() >= weight) {
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
    public void testColor() {
        List<Apple> appleList = filterGreenApple(this.apples);
        for (Apple apple : appleList) {
            System.out.println(apple);
        }


    }

    @Test
    public void testColor2() {
        List<Apple> appleList = filterGreenApple(this.apples, "red");
        for (Apple apple : appleList) {
            System.out.println(apple);
        }


    }

    @Test
    public void testWeight() {
        List<Apple> weightList = filterWeightApple(this.apples, 500);
        for (Apple apple : weightList) {
            System.out.println(apple);
        }
    }


}
