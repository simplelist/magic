package chain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Client {
    @Test
    public void testChain() {
        Random rand = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<IWomen>();

        for (int i = 0; i < 5; i++) {
            arrayList.add(new Women(rand.nextInt(4), "我要出去逛街"));
        }

        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();
        father.setNext(husband);
        husband.setNext(son);

        for (IWomen women : arrayList) {
            father.handleMessage(women);
        }
    }
}
