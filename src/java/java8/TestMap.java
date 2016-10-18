package java8;

import java8.test_method.Person;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *正如前面已经提到的那样，map是不支持流操作的。而更新后的map现在则支持多种实用的新方法，来完成常规的任务。
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestMap {
    @Test
    public void test1(){
        Map<Integer,String> map=new HashMap<>();
        for (int i=0;i<10;i++){
            map.putIfAbsent(i,"val"+i);
        }
//        map.forEach((id,val)-> System.out.println(val));

        map.computeIfPresent(3,(num,val)->val+num);
        System.out.println(map.get(3));

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));

        System.out.println(map.getOrDefault(42, "not found"));  // not found
    }
}
