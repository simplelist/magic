package java8.test_functional;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestFunctional {
    @Test
    public void test1(){
        Converter<String,Integer> converter=(from)->Integer.valueOf(from);

        Integer a=converter.convert("3453");
        System.out.println(a);
    }

    @Test
    public void test2(){
        Converter<String,Integer> converter=Integer::valueOf;

        Integer a=converter.convert("34553");
        System.out.println(a);
    }
}
