package java8;

import java8.test_method.Person;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Consumer代表了在一个输入参数上需要进行的操作。
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestComparators {
    @Test
    public void test1(){
        Comparator<Person> comparator=(p1,p2)->p1.getFirstName().compareTo(p2.getFirstName());
        Person p1=new Person("John","Doe");
        Person p2=new Person("Alice","Eonderland");
        System.out.println(comparator.compare(p1,p2));
    }
}
