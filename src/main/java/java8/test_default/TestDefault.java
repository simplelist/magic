package java8.test_default;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestDefault {
    @Test
    public void test1(){
        Formula formula=new Formula() {
            @Override
            public double calculate(int a) {
                return a*2016;
            }
        };
        System.out.println(formula.calculate(5));
        System.out.println(formula.sqrt(16));
    }
}
