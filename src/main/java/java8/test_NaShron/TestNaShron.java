package java8.test_NaShron;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestNaShron {
    @Test
    public void test1(){
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval("print('hello world')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    /*在Java中调用JavaScript函数*/
    @Test
    public void test2(){
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("./src/java/java8/test_NaShron/a.js"));

            Invocable invocable= (Invocable) engine;
            Object result=invocable.invokeFunction("fun1","Peter Parker");
            System.out.println(result);
            System.out.println(result.getClass());

            invocable.invokeFunction("fun2",new Date());
            invocable.invokeFunction("fun2", LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("./src/java/java8/a.js"));

            Invocable invocable= (Invocable) engine;
            Object result=invocable.invokeFunction("fun1","Peter Parker");
            System.out.println(result);
            System.out.println(result.getClass());

            invocable.invokeFunction("fun2",new Date());
            invocable.invokeFunction("fun2", LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String fun1(String name){
        System.out.format("Hi there from Java, %s",name);
        return "greeting from java";
    }

}
