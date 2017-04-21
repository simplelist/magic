package observer;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Lisi implements Observer {

    //首先李斯是个观察者,一旦韩非子有活动,他就知道,向老板汇报
    public void update(String context) {
        System.out.println("李斯说:观察到韩非子活动,开始向老板汇报...");
        this.reportToQin(context);
        System.out.println("李斯说:汇报完毕.");
    }

    private void reportToQin(String context) {
        System.out.println("李斯:报告秦老板,韩非子有活动了-->" + context);
    }
}
