package thread.wait_notify_size5;

/**
 * Created by kun on 2017/4/26.
 */
public class ThreadA extends Thread{
    private Object lock;

    public ThreadA(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                if (MyList.size()!=5){
                    System.out.println("开始等待"+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("结束等待"+System.currentTimeMillis());

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
