package state;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Client {
    @Test
    public void test1() {
        On on = new On();
        Off off = new Off();
        Light light = new Light(on);
        light.setState(on);
        light.pressSwitch();

        light.setState(off);
        light.pressSwitch();
    }
}
