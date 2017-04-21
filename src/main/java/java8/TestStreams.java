package java8;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestStreams {
    @Test
    public void test1() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        //aaa2 aaa1
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        //aaa1 aaa2
        /*一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。原来string集合中的元素顺序是没有改变的。*/
        System.out.println(stringCollection);
        //[ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1]


        /*Map*/
        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> a.compareTo(b)).forEach(System.out::println);

        boolean anyStartsWithA = stringCollection.stream().anyMatch((s -> s.startsWith("a")));
        System.out.println(anyStartsWithA);
        //true
        boolean allStartsWithA = stringCollection.stream().allMatch((s -> s.startsWith("a")));
        System.out.println(allStartsWithA);
        //false
        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s -> s.startsWith("a")));
        System.out.println(noneStartsWithZ);
        //false


        /*Count
        * Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的元素数量。
        * */
        long startWithB = stringCollection.stream().filter(s -> s.startsWith("b")).count();
        System.out.println(startWithB);//3

        /*Reduce
该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。*/
        Optional<String> reduced=stringCollection.stream().sorted().reduce((s1,s2)->s1+"#"+s2);
        reduced.ifPresent(System.out::println);//aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
    }
}
