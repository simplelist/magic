package factory;

/**
 * Created by simplelist on 2016/9/17.
 */
public abstract class AbstractHumanFactory {
    public abstract <T extends Human> T createHuman(Class<T> c);
}
