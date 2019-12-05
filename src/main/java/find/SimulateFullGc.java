package find;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 胡说
 * @data 2019-11-07 14:03
 */
public class SimulateFullGc {
    public static void main(String[] args)
    {
        //模拟fullgc场景
        //场景1 使用System.gc
        List<Object> l = new ArrayList<>();
        for (int i =0; i< 100;i++)
        {
            l.add(new byte[1024*1024 ]);
            if (i % 10 ==0)
            {
                System.gc();
            }
        }

    }
}
