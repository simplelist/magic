package redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by jackshi on 16/8/30.
 */
public class TestRedis {
    Jedis jedis;

    /**
     * 测试服务器是否打开,是否连接到服务器
     */
    @Test
    public void t1() {
        System.out.println("Serve is running:" + jedis.ping());
    }

    /**
     * 测试字符串的设置和获取
     */
    @Test
    public void t2() {
        jedis.set("w3ckey", "Redis tutorial");
        System.out.println("Stored stringin redis:" + jedis.get("w3ckey"));
    }

    /**
     * 测试Map的设置和获取
     */
    @Test
    public void t3() {
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        List<String> list = jedis.lrange("tutorial-list", 0, 5);
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Before
    public void before() {
        jedis = new Jedis("localhost");
        System.out.println("connection successfully");
    }

}
