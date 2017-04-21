package templateMethod;

/**
 * Created by simplelist on 2016/9/17.
 */
public abstract class HummerModel {
    private boolean alarmFlag = true;

    public abstract void start();

    public abstract void stop();

    public abstract void alarm();

    public abstract void engineBoom();

    final public void run() {
        this.start();
        this.engineBoom();
        if (this.isAlarm()) {
            this.alarm();
        }
        this.stop();
    }

    public abstract boolean isAlarm();

}
