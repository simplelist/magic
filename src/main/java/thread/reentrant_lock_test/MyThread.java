package thread.reentrant_lock_test;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class MyThread extends Thread {
    private MyService myService;

    public MyThread(MyService myService) {
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
