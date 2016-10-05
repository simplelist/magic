package observer;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Client {
    @Test
    public void test1() {
        Observer lisi = new Lisi();

        HanFeiZi hanFeiZi = new HanFeiZi();
        hanFeiZi.add(lisi);
        hanFeiZi.haveBreakfast();
        hanFeiZi.haveFun();
    }
}
