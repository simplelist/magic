package java8;

import org.junit.Test;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestPredicates {
    @Test
    public void test1(){
        Predicate<String> predicate=(s)->s.length()>0;
        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));

        Predicate<Boolean> nonNull= Objects::nonNull;
        Predicate<Boolean> isNull=Objects::isNull;

        Predicate<String> isEmpty=String::isEmpty;
        Predicate<String> isNotEmpty=isEmpty.negate();
    }
}
