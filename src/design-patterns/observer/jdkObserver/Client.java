package observer.jdkObserver;


import observer.Observer;
import org.junit.Test;

import java.util.Observable;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Client {
    @Test
    public void test1() {
        java.util.Observer lisi = new LiSi();

        HanFeiZi hanFeiZi = new HanFeiZi();
        hanFeiZi.addObserver(lisi);
        hanFeiZi.haveBreakfast();
        hanFeiZi.haveFun();
    }
}
