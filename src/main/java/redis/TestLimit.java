package redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by jackshi on 16/8/30.
 */
public class TestLimit {
    Jedis jedis;

    /**
     * 测试服务器是否打开,是否连接到服务器
     */
    @Test
    public void t1() {
    }

    /**
     * 测试字符串的设置和获取
     */
    @Test
    public void t2() {

    }


    @Before
    public void before() {
        jedis = new Jedis("localhost");
        System.out.println("connection successfully");
    }

}
