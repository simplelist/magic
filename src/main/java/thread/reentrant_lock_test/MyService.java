package thread.reentrant_lock_test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class MyService {
    private Lock lock=new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i=0;i<50;i++) {
            System.out.println("ThreadName:"+Thread.currentThread().getName()+"-"+(i+1));
        }
        lock.unlock();
    }
}
