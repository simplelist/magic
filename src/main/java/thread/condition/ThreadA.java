package thread.condition;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class ThreadA extends Thread {
    private MyService service=new MyService();

    public ThreadA(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.await();
    }
}
