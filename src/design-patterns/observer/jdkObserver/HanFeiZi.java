package observer.jdkObserver;

import observer.IHanFeiZi;

import java.util.Observable;

/**
 * Created by Administrator on 2016/10/5.
 */
public class HanFeiZi extends Observable implements IHanFeiZi {
    public void haveBreakfast() {
        System.out.println("韩非子开始吃早饭了");
        super.setChanged();
        super.notifyObservers("韩非子在吃饭");
    }

    public void haveFun() {
        System.out.println("韩非子在娱乐了");
        super.setChanged();
        super.notifyObservers("韩非子在娱乐");
    }
}
