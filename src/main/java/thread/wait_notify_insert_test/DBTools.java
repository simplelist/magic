package thread.wait_notify_insert_test;

/**
 * Created by kun on 2017/4/26.
 */
public class DBTools {

    /**
     * 交替执行
     */
    volatile private boolean prevIsA=false;

    synchronized public void backupA(){
        try {
            while (prevIsA){
                wait();
            }
            for (int i=0;i<5;i++){
                System.out.println("※");
            }
            prevIsA=true;
            notifyAll();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    synchronized public void backupB(){
        try {
            while (!prevIsA){
                wait();
            }
            for (int i=0;i<5;i++){
                System.out.println("\\(^o^)/~");
            }
            prevIsA=false;
            notifyAll();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
