package java8.test_default;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public interface Formula {
    double calculate(int a);


    /*默认方法无法在lambda表达式内部被访问*/
    default double sqrt(int a) {
        /*因此下面的代码是无法通过编译的：
        * Formula formula = (a) -> sqrt( a * 100);
        * */
        return Math.sqrt(a);
    }
}
