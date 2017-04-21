package proxy;

import org.junit.Test;

/**
 * Created by simplelist on 2016/9/19.
 */
public class Client {

    @Test
    public void testGame() {
        IGamePlayer player = new GamePlayer("蓝孩子");

        IGamePlayer proxy = new GamePlayerProxy(player);
        proxy.login("boy", "password");
        proxy.killBoss();
        proxy.upgrade();
    }
}
