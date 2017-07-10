package java8.test_lambda;

import java8.test_functional.Converter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestLambda {
    @Test
    public void testLambda1() {
        List<String> names = Arrays.asList("pete", "anna", "jack", "mike", "harry");

       /* Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });*/
        System.out.println(names);

        //lambda
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);
    }

    @Test
    public void testLambda2() {
        /*能够访问局部对应的外部区域的局部final变量，以及成员变量和静态变量。*/
        int num = 1;
        Converter<Integer, String> converter = (from) -> String.valueOf(from + num);
        System.out.println(converter.convert(2));
        /*然而，num在编译的时候被隐式地当做final变量来处理。下面的代码就不合法：*/
//        num=3;
    }

    static int outStaticNum;
    int outerNum;

    @Test
    public void testLambda3() {
        Converter<Integer, String> converter = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        Converter<Integer,String> converter1=(from)->{
            outStaticNum=76;
            return String.valueOf(from);
        };

        System.out.println(converter.convert(56));
        System.out.println(converter1.convert(27));
    }


}
