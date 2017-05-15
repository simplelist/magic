package other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public class ConnectionPool {
    private List<Connection> pool = new ArrayList<>();

    int size;

    public ConnectionPool(int size) {
        this.size = size;
        init();
    }

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                pool.add(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "kun", "654321"));
            }
        } catch (Exception e) {

        }
    }

    public synchronized Connection getConnection() {
        while (pool.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return pool.remove(0);
    }

    public synchronized void returnConnection(Connection c){
        pool.add(c);
        this.notifyAll();
    }
}
