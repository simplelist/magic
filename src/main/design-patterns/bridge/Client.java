package bridge;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Client {
    @Test
    public void test1() {
        House house = new House();
        HouseCorp houseCorp = new HouseCorp(house);

        houseCorp.makeMoney();

        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Clothes());
        shanZhaiCorp.makeMoney();
    }
}
