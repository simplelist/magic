package templateMethod;

/**
 * Created by simplelist on 2016/9/17.
 */
public class HummerH1Model extends HummerModel {

    @Override
    public void start() {
        System.out.println("悍马H1发动");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车");
    }

    @Override
    public void alarm() {
        System.out.println("悍马鸣笛");

    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H1引擎是这样的");
    }

    private boolean alarmFlag = true;

    @Override
    public boolean isAlarm() {
        return alarmFlag;
    }

    public void setAlarm(boolean isAlarm) {
        alarmFlag = isAlarm;
    }

}
