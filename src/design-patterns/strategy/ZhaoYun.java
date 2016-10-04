package strategy;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/4.
 */
public class ZhaoYun {
    @Test
    public void testIS() {
        Context context;
        context = new Context(new BackDoor());
        context.operator();


        context = new Context(new GivenGreenLight());
        context.operator();

        context = new Context(new BlockEnemy());
        context.operator();

    }
}
