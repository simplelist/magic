package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by kun on 2017/4/19.
 */
public class TestNewFeatures {
    public static void main(String args[]){
        TestNewFeatures tester=new TestNewFeatures();

        //带有类型声明的表达式
        MathOperation addtion=(int a,int b)->a+b;
        //没有类型声明的表达式
        MathOperation substraction=(a,b)->a-b;
        //带有大括号,带有返回语句的表达式
        MathOperation multiplication=(int a,int b)->{return a*b;};
        //没有大括号和return语句的表达式
        MathOperation division=(int a,int b)->a/b;


        System.out.println("10+5="+tester.operate(100,5,addtion));
        System.out.println("10-5="+tester.operate(100,5,substraction));
        System.out.println("10*5="+tester.operate(100,5,multiplication));
        System.out.println("10/5="+tester.operate(100,5,division));


        //没有括号的表达式
        GreetingService greetingService=message-> System.out.println("Hello "+message);
        //有括号的表达式
        GreetingService greetingService2=(message)-> System.out.println("Hello "+message);
        //调用sayMessage方法输出结果
        greetingService.sayMessage("Shiyanlou");
        greetingService2.sayMessage("Classmate");


        List<Integer> list= Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        System.out.println("All numbers:");
        eval(list,n->true);
        System.out.println("偶数:");
        eval(list,n->n%2==0);
        System.out.println("大于5的数字");
        eval(list,n-> n> 5);

        Boy boy=new Wang();
        boy.print();

    }

    //定义接口和方法
    interface MathOperation{
        int operation(int a,int b);
    }
    interface GreetingService{
        void sayMessage(String msg);
    }
    private int operate(int a,int b,MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n : list) {
            if (predicate.test(n)){
                System.out.println(n);
            }
        }
    }
    public interface Boy{
        default void print(){
            System.out.println("I am a boy.");
        }
    }
}
class Wang implements TestNewFeatures.Boy {

}
