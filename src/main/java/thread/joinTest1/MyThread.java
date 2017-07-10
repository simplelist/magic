package thread.joinTest1;

/**
 * Created by kun on 2017/4/26.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            int secondValue=(int)(Math.random()*10000);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
