package strategy;

/**
 * Created by Administrator on 2016/10/4.
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operator() {
        this.strategy.operate();
    }
}
