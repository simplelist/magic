package builder;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by simplelist on 2016/9/18.
 */
public class Client {
    @Test
    public void testModel() {
        BenzModel benz = new BenzModel();
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        benz.setSequece(sequence);
        benz.run();
    }

}
