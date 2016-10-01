package factory;

/**
 * Created by simplelist on 2016/9/17.
 */
public class WhiteHuman implements Human {
    public void getColor() {
        System.out.println("白皮肤");
    }

    public void talk() {
        System.out.println("一般都是单字节");
    }
}
