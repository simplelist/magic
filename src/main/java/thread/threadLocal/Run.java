package thread.threadLocal;

import org.junit.Test;

/**
 * Created by kun on 2017/4/26.
 */
public class Run {

    public static ThreadLocal tl=new ThreadLocal();
    public static void main(String args[]){
        if (tl.get()==null){
            System.out.println("空的");
            tl.set("我的值");
        }
        System.out.println(tl.get());
        System.out.println(tl.get());

    }


    @Test
    public void test() {
        try {
            ThreadA a=new ThreadA();
            ThreadB b=new ThreadB();
            a.start();
            b.start();

            for (int i=0;i<100;i++){
                Tools.tl.set("Main "+(i+1));
                System.out.println("Main get Value="+Tools.tl.get());
                Thread.sleep(200);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
