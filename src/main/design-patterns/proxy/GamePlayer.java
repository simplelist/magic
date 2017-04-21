package proxy;

/**
 * Created by simplelist on 2016/9/19.
 */
public class GamePlayer implements IGamePlayer {

    private String name = "";

    public GamePlayer(String name) {
        this.name = name;
    }

    public void login(String user, String password) {
        System.out.println("登录名为" + user + "的用户" + this.name + "登录成功!");
    }

    public void killBoss() {
        System.out.println("在打Boss!");
    }

    public void upgrade() {
        System.out.println(this.name + "又升了一级!");
    }
}
