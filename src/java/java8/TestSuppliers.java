package java8;

import java8.test_method.Person;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestSuppliers {
    @Test
    public void test1(){
        Supplier<Person> personSupplier=Person::new;
        System.out.println(personSupplier.get());
    }
}
