package redis;

import org.junit.Test;

/**
 * @author 胡说
 * @data 2019-09-06 17:34
 * TODO
 */
public class Pub extends BaseRedisTest {
    @Test
    public void test() {
        jedis.publish("doReBalance", "accccaa");
    }
}
