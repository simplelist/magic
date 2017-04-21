package java8.test_functional;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
