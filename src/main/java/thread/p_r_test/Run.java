package thread.p_r_test;

/**
 *
 * 生产者,消费者
 * Created by kun on 2017/4/26.
 */
public class Run {

    public static void main(String args[]){
        String lock=new String("");
        P p=new P(lock);
        C c=new C(lock);

        ThreadP pThread=new ThreadP(p);
        ThreadC cThread=new ThreadC(c);

        pThread.start();
        cThread.start();
    }
}
