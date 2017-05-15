package thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class MyService {
    private Lock lock=new ReentrantLock();
    public Condition condition=lock.newCondition();

    public void await(){
        try {
            lock.lock();
            System.out.println("await()等待时间为:"+System.currentTimeMillis());
            condition.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        try {
            lock.lock();
            System.out.println("signal时间为:"+System.currentTimeMillis());
            condition.signal();

        }finally {
            lock.unlock();
        }
    }
}
