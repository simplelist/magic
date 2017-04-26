package thread.wait_notify_insert_test;

/**
 * Created by kun on 2017/4/26.
 */
public class Run {
    public static void main(String args[]){
        DBTools dbTools=new DBTools();
        for (int i=0;i<2;i++){
            BackupB output=new BackupB(dbTools);
            output.start();

            BackupA input=new BackupA(dbTools);
            input.start();

        }
    }
}
