package other;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public class WorkingThread extends Thread {


    public static void main(String[] args){
        ConnectionPool cp=new ConnectionPool(3);
        for (int i = 0; i <100 ; i++) {
            new WorkingThread("线程"+i,cp);
        }
    }
    private ConnectionPool pool;

    public WorkingThread(String name,ConnectionPool pool) {
        super(name);
        this.pool = pool;
    }

    @Override
    public void run() {

        Connection c=pool.getConnection();
        System.out.println(this.getName()+"获取了一个连接，开始工作");
        try {
           Statement s=c.createStatement();
            Thread.sleep(1000);
            s.execute("select now()");

        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.returnConnection(c);
    }
}
