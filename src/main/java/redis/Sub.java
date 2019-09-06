package redis;

import org.junit.Test;
import redis.clients.jedis.JedisPubSub;

/**
 * @author 胡说
 * @data 2019-09-06 17:34
 * TODO
 */
public class Sub extends BaseRedisTest {

    @Test
    public void test() {
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel + "::::" + message);
            }
        }, "doReBalance");
    }
}
