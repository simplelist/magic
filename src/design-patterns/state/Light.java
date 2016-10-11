package state;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Light {
    public Light(LightState state) {
        this.state = state;
    }

    private LightState state;

    public void pressSwitch() {
        state.pressSwitch();
    }

    public LightState getState() {
        return state;
    }

    public void setState(LightState state) {
        this.state = state;
    }
}
