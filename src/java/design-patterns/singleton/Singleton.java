package singleton;

/**
 * Created by simplelist on 2016/9/17.
 */
public class Singleton {
    private static final Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
