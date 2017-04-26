package thread.waitReleaseLock;

/**
 * Created by kun on 2017/4/26.
 */
public class ThreadA  extends Thread{
    private Object lock;

    public ThreadA(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service=new Service();
        service.testMethod(lock);
    }
}
