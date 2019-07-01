package redis;

import java.lang.annotation.*;

/**
 * @Author 胡说
 * @DATETIME 2019-07-01 19:12
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    int count() default 0;
}
