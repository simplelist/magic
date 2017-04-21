package proxy.dynamic;

import org.junit.Test;
import proxy.GamePlayer;
import proxy.IGamePlayer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by simplelist on 2016/9/19.
 */
public class Client {

    @Test
    public void testGamePlayH() {
        IGamePlayer player = new GamePlayer("吕孩子");
        InvocationHandler handler = new GamePlayH(player);

        ClassLoader cl = player.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, handler);

        proxy.login("girl", "123");
        proxy.killBoss();
        proxy.upgrade();
    }
}
