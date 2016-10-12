package bridge;

/**
 * Created by Administrator on 2016/10/12.
 */
public abstract class Corp {
    private Product produce;

    public Corp(Product produce) {
        this.produce = produce;
    }

    public void makeMoney() {
        this.produce.beProducted();
        this.produce.beSelled();
    }
}
