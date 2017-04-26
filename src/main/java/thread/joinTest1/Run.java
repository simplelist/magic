package thread.joinTest1;

/**
 * Created by kun on 2017/4/26.
 */
public class Run {
    public static void main(String args[]){
        MyThread thread=new MyThread();
        thread.start();
        try {
            thread.join(5000);
            /**
             * 没有thread.join()时,下边那句就直接执行了,
             * 有了thread.join()时,下边那句要等thread执行完成才会执行
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("我想当MyThread执行完毕我再执行");
    }
}
