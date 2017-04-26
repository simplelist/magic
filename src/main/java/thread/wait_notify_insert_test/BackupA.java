package thread.wait_notify_insert_test;


/**
 * Created by kun on 2017/4/26.
 */
public class BackupA extends Thread{

    private DBTools dbTools;

    public BackupA(DBTools dbTools) {
        super();
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}
