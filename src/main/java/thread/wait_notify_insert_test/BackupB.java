package thread.wait_notify_insert_test;


/**
 * Created by kun on 2017/4/26.
 */
public class BackupB extends Thread{

    private DBTools dbTools;

    public BackupB(DBTools dbTools) {
        super();
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}
