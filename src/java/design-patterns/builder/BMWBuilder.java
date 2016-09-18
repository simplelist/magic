package builder;

import java.util.ArrayList;

/**
 * Created by simplelist on 2016/9/18.
 */
public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequece(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
}
