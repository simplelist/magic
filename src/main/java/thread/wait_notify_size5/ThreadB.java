package thread.wait_notify_size5;

/**
 * Created by kun on 2017/4/26.
 */
public class ThreadB extends Thread{
    private Object lock;

    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                for(int i=0;i<10 ;i++){
                    MyList.add();
                    if (MyList.size()==5){
                        lock.notify();
                        System.out.println("发出notify");
                    }
                    System.out.println("添加了"+(i+1)+"个元素");
                    Thread.sleep(1000);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
