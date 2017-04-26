package thread.wait_notify_size5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kun on 2017/4/26.
 */
public class MyList {
    private static List list=new ArrayList();
    public static void add(){
        list.add("anyString");
    }
    public static int size(){
        return list.size();
    }
}
