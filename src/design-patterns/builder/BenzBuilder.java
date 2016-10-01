package builder;

import java.util.ArrayList;

/**
 * Created by simplelist on 2016/9/18.
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequece(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
