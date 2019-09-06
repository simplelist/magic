package redis;

import org.junit.Before;
import redis.clients.jedis.Jedis;

/**
 * @author 胡说
 * @data 2019-09-06 17:35
 * TODO
 */
public class BaseRedisTest {
    public Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis();
    }

}
