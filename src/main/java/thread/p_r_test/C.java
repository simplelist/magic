package thread.p_r_test;

/**
 * Created by kun on 2017/4/26.
 */
public class C {

    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue(){
        try {
            synchronized (lock){
                if (ValueObject.value.equals("")){
                    lock.wait();
                }

                System.out.println("get的值是:"+ValueObject.value);
                ValueObject.value="";
                lock.notify();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
