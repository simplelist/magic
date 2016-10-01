package singleton;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by simplelist on 2016/9/17.
 */
public class Client {
    @Test
    public void testSingleton() {
        Singleton s1 = Singleton.getSingleton();
        Assert.assertTrue(Singleton.getSingleton().equals(s1));
    }

}
