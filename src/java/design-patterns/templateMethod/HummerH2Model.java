package templateMethod;

/**
 * Created by simplelist on 2016/9/17.
 */
public class HummerH2Model extends HummerModel {
    @Override
    public void start() {
        System.out.println("悍马H2发动");
    }

    @Override
    public void stop() {
        System.out.println("悍马H2停车");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H2鸣笛");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H2引擎是这样的");
    }

    @Override
    public boolean isAlarm() {
        return false;
    }


}
