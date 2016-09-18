package builder;

import java.util.ArrayList;

/**
 * Created by simplelist on 2016/9/18.
 */
public abstract class CarBuilder {
    public abstract void setSequence(ArrayList<String> sequence);

    public abstract CarModel getCarModel();
}
