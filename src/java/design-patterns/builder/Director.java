package builder;

import java.util.ArrayList;

/**
 * 导演类,负责生产某一车型
 * Created by simplelist on 2016/9/18.
 */
public class Director {
    private ArrayList<String> sequence = new ArrayList<String>();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    public BenzModel getABenzModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public BenzModel getBBenzModel() {
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.sequence.add("alarm");
        this.benzBuilder.setSequence(sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public BMWModel getCBMWModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.sequence.add("stop");
        this.sequence.add("alarm");
        this.bmwBuilder.setSequence(sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    public BMWModel getDBMWModel() {
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("alarm");
        this.sequence.add("stop");
        this.sequence.add("alarm");
        this.bmwBuilder.setSequence(sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }
}
