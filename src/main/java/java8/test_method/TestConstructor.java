package java8.test_method;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestConstructor {
    @Test
    public void test1(){
        PersonFactory<Person> personFactory=Person::new;
        Person person=personFactory.create("a","b");
        System.out.println(person);
    }
}

interface PersonFactory<P extends Person>{
    P create(String firstName,String lastName);
}
