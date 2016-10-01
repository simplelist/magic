package builder;

/**
 * Created by simplelist on 2016/9/18.
 */
public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("奔驰车是这样启动的");

    }

    @Override
    protected void stop() {
        System.out.println("奔驰车是这样停止的");

    }

    @Override
    protected void alarm() {
        System.out.println("奔驰车这样鸣笛");

    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎是这样");
    }
}
