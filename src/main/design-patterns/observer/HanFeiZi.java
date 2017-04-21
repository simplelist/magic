package observer;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/5.
 */
public class HanFeiZi implements Observable, IHanFeiZi {
    //存放所有的观察者
    private ArrayList<Observer> observerList = new ArrayList<Observer>();

    public void haveBreakfast() {
        System.out.println("韩非子开始吃早饭了");
        this.notify("韩非子开始吃早饭了");
    }

    public void haveFun() {
        System.out.println("韩非子在娱乐了");
        this.notify("韩非子娱乐了");
    }

    public void add(Observer observer) {
        this.observerList.add(observer);
    }

    public void remove(Observer observer) {
        this.observerList.remove(observer);
    }

    public void notify(String context) {
        for (Observer observer : observerList) {
            observer.update(context);
        }
    }
}
