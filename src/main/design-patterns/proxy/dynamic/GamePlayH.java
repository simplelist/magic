package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by simplelist on 2016/9/19.
 */
public class GamePlayH implements InvocationHandler {

    Class cls = null;

    /*被代理的对象*/
    Object obj = null;

    public GamePlayH(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("upgrade")) {
            new BeforeAdvice().exec();
        }
        Object result = method.invoke(this.obj, args);
        if (method.getName().equalsIgnoreCase("login")) {
            System.out.println("有人登录我的账号");
        }

        return result;
    }
}
