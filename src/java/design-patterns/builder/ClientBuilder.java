package builder;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by simplelist on 2016/9/18.
 */
public class ClientBuilder {
    @Test
    public void testModel() {

        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        sequence.add("alarm");

        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setSequence(sequence);

        BenzModel benz = (BenzModel) benzBuilder.getCarModel();
        benz.run();
    }

}
