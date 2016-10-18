package java8;

import java8.test_method.Person;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Consumer代表了在一个输入参数上需要进行的操作。
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestConsumers {
    @Test
    public void test1(){
        Consumer<Person> greeter=(p)-> System.out.println("hello, "+p.getFirstName());
        Person p1=new Person("John","Doe");
        greeter.accept(p1);
    }
}
