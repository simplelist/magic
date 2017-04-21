package composite;

/**
 * Created by Administrator on 2016/10/5.
 */
public abstract class Component {

    //个体和整体都具有的共享
    public void doSomething() {
        //业务逻辑
        System.out.println("do some thing");
    }
}
