package thread.p_r_test;

/**
 * Created by kun on 2017/4/26.
 */
public class P {
    private String lock;

    public P(String lock) {
        this.lock = lock;
    }

    public void setValue(){
        try {
            synchronized (lock){
                if (!ValueObject.value.equals("")){
                    lock.wait();
                }

                String value=System.currentTimeMillis()+"_"+System.nanoTime();
                System.out.println("set的值是:"+value);
                ValueObject.value=value;
                lock.notify();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
