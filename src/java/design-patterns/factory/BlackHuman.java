package factory;

/**
 * Created by simplelist on 2016/9/17.
 */
public class BlackHuman implements Human {
    public void getColor() {
        System.out.println("黑色人种,皮肤是黑的");
    }

    public void talk() {
        System.out.println("黑人说话一般听不懂");
    }
}
