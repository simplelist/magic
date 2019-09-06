package redis.rate;

import org.junit.Test;
import redis.BaseRedisTest;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * @author 胡说
 * @data 2019-09-02 20:32
 * TODO
 */
public class RateLimit extends BaseRedisTest {

    public boolean isActionAllowed(String key, int period, int maxCount) throws IOException {
        long timeMillis = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();

        //删除本 key 中当前轮次时间外的所有 value
        pipeline.zremrangeByScore(key, 0, timeMillis - period * 1000);
        //这里使用 zcard 而不是用 zcount 不用指定时间,直接返回所有的
        Response<Long> count = pipeline.zcard(key);
        //如果现有的 集合数量小于当前限定值,返回可用.
        pipeline.exec();
        pipeline.close();

        return count.get() < maxCount;
    }

    @Test
    public void test() throws IOException {
        String key = "abc";
        long currentTimeMillis = System.currentTimeMillis();
        if (isActionAllowed(key, 10, 1)) {
            jedis.zadd(key, currentTimeMillis, "" + currentTimeMillis);
        } else {
            System.out.println("超限了");
        }
    }
}
