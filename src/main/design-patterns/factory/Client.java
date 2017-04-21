package factory;

import org.junit.Test;

/**
 * Created by simplelist on 2016/9/17.
 */
public class Client {
    @Test
    public void testHuman() {
        AbstractHumanFactory YinYangLu = new HumanFactory();
        Human whiteMan = YinYangLu.createHuman(WhiteHuman.class);
        whiteMan.getColor();
        whiteMan.talk();

        Human blackMan = YinYangLu.createHuman(BlackHuman.class);
        blackMan.getColor();
        blackMan.talk();

        Human yellowMan = YinYangLu.createHuman(YellowHuman.class);
        yellowMan.getColor();
        yellowMan.talk();
    }
}
