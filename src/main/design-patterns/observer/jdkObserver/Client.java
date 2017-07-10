package observer.jdkObserver;


import org.junit.Test;

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
