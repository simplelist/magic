package java8;

import java8.test_method.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 像上面所说的，流操作可以是顺序的，也可以是并行的。顺序操作通过单线程执行，而并行操作则通过多线程执行。
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestParallel {
    @Test
    public void test1(){
       int max=1000000;
        List<String> values=new ArrayList<>();
        for (int i=0;i<max;i++){
            UUID uuid= UUID.randomUUID();
            values.add(uuid.toString());
        }

//        long t0 = System.nanoTime();
//
//        long count = values.stream().sorted().count();
//        System.out.println(count);
//
//        long t1 = System.nanoTime();
//
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("sequential sort took: %d ms", millis));
//        sequential sort took: 822 ms

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
        //parallel sort took: 510 ms
    }
}
