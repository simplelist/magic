package redis;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author 胡说
 * @DATETIME 2019-07-01 19:43
 */
public class LimitService {
    private static final int count = 2;
    private static final int second = 10;
    //数值变化更新有效期
    private static final boolean INCREASE_VALUE_REFRESH_TTL = false;
    private static final String key = "limit_component";
    private final String value = "value";
    private final String createTime = "create_time";
    Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("localhost");
    }

    @Test
    public void test() {
        execute();
    }

    public void execute() {
        if (check()) {
            System.out.println("执行方法");
        }
    }

    private boolean check() {
        boolean result;
        int currentCount = 0;
        if (!jedis.exists(key)) {
            result = true;
            setKey(currentCount);
        } else {
            String s = jedis.hget(key, value);
            currentCount = Convert.toInt(s);
            if (currentCount >= count) {
                //大于等于超限啦
                result = false;
            } else {
                result = true;
                setKey(currentCount);
            }
        }
        System.out.println(StrUtil.format("检查结果【{}】", result));
        return result;
    }

    private void setKey(int currentCount) {
        currentCount++;
        Boolean exists = jedis.exists(key);
        jedis.hset(key, value, Convert.toStr(currentCount));
        if (INCREASE_VALUE_REFRESH_TTL || !exists) {
            //续期
            jedis.expire(key, second);
            if (!exists) {
                jedis.hset(key, createTime, Convert.toStr(System.currentTimeMillis()));
            }
        }
    }


}
