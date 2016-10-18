package java8;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestOptionals {
    @Test
    public void test1(){
        Optional<String> optional=Optional.of("bam");
        System.out.println(optional.isPresent());
        System.out.println(optional.orElse("fallback"));
        System.out.println(optional.get());


        optional.ifPresent((s)-> System.out.println(s.charAt(0)));
    }
}
