package thread.waitReleaseLock;


/**
 * Created by kun on 2017/4/26.
 */
public class Run {
    public static void main(String args[]){
        try {
            Object lock=new Object();
            ThreadA a=new ThreadA(lock);
            a.start();
            ThreadB b=new ThreadB(lock);
            b.start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
