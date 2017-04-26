package thread.waitReleaseLock;

/**
 * Created by kun on 2017/4/26.
 */
public class Service {
    public void testMethod(Object lock){
        try {
            synchronized (lock){
                System.out.println("开始等待");
                lock.wait();
                System.out.println("结束等待");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
