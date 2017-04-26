package thread.p_r_test;

/**
 * Created by kun on 2017/4/26.
 */
public class ThreadC extends Thread{
    private C c;


    public ThreadC(C c) {
        super();
        this.c = c;
    }

    @Override
    public void run() {
        while (true){
            c.getValue();
        }
    }
}
