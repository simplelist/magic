package java8;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * 新的时间日期API是基于Joda-Time库开发的，但是也不尽相同。
 * Created by Administrator on 2016/10/18 0018.
 */
public class TestDate {
    @Test
    public void test1() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

    @Test
    public void test2() {
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Asia/Aden");
        System.out.println(zone1.getRules());
    }


}
