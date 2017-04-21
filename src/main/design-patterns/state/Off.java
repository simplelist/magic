package state;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Off implements LightState {

    public void pressSwitch() {
        System.out.println("灯关闭了");
    }
}
