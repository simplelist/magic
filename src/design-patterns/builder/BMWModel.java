package builder;

/**
 * Created by simplelist on 2016/9/18.
 */
public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("宝马车是这样启动的");
    }

    @Override
    protected void stop() {
        System.out.println("宝马车是这样的停止");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马车是这样的鸣笛");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马车引擎是这样的");
    }
}
