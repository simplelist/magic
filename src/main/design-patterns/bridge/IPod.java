package bridge;

import javax.sound.midi.Soundbank;

/**
 * Created by Administrator on 2016/10/12.
 */
public class IPod extends Product {

    public void beProducted() {
        System.out.println("生产IPod");
    }

    public void beSelled() {
        System.out.println("销售IPod");
    }
}
