package java8.test_method;

import java8.test_functional.Converter;
import org.junit.Test;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestMethod {

    @Test
    public void test1(){
        Something something=new Something();
        Converter<String,String> converter=something::startsWith;
        String a=converter.convert("Java");
        System.out.println(a);
    }
}
class Something{
    String startsWith(String s){
        return String.valueOf(s.charAt(0));
    }


}
