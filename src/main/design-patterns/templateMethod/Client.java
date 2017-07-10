package templateMethod;

import org.junit.Test;

/**
 * Created by simplelist on 2016/9/17.
 */
public class Client {
    @Test
    public void testHummer() {
        HummerH1Model h1 = new HummerH1Model();
        h1.setAlarm(true);
        h1.run();

        HummerH2Model h2 = new HummerH2Model();
        h2.run();
    }
}
