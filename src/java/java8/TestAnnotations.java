package java8;

import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * Java 8中的注解是可重复的。
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestAnnotations {
    @Test
    public void test1() {

    }




}
@interface Hints{
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint{
    String value();
}

@Hint("hint1")
@Hint("hint2")
class A{}