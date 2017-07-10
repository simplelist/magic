package java8;

import org.junit.Test;

import java.util.function.Function;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestFunctions {
    @Test
    public void test1(){
        Function<String,Integer> toInteger=Integer::valueOf;
        Function<String,String> backToStirng=toInteger.andThen(String::valueOf);
        System.out.println(backToStirng.apply("123"));
    }
}
