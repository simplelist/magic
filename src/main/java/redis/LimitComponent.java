package redis;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author 胡说
 * @DATETIME 2019-07-01 19:11
 */
@Aspect
public class LimitComponent {

    @Pointcut(value = "@annotation(Limit)")
    public void cut() {

    }

    @Before(value = "cut()")
    public void check() {
        System.out.println("check()");
    }
}
