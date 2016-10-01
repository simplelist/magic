package proxy.dynamic;

import com.xiaoleilu.hutool.lang.DateTime;

/**
 * Created by simplelist on 2016/9/19.
 */
public class BeforeAdvice implements IAdvice {
    /*前置通知*/
    public void exec() {
        System.out.println("----------------------------------");
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss") + " 我是前置通知,我被执行了");
        System.out.println("----------------------------------");
    }
}
