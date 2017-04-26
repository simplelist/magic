package thread.p_r_test;

/**
 * Created by kun on 2017/4/26.
 */
public class ThreadP extends Thread{
    private P p;


    public ThreadP(P p) {
        super();
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            p.setValue();
        }
    }
}
