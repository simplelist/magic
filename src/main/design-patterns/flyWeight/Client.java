package flyWeight;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/11.
 */
public class Client {
    @Test
    public void test1() {
        SignInfo signInfo = SignInfoFactory.getSignInfo();
    }

    @Test
    public void test2() {
        for (int i = 0; i < 4; i++) {
            String subject = "科目" + i;
            for (int j = 0; j < 30; j++) {
                String key = subject + "考试地点" + j;
                SignInfoFactory.getSignInfo(key);
            }
        }
        for (int i = 0; i < 10000; i++) {
            SignInfo signInfo = SignInfoFactory.getSignInfo("科目1考试地点1");
            System.out.println(" ");
        }
    }
}
