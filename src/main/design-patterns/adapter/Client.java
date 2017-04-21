package adapter;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Client {
    @Test
    public void test1() {
        IUserInfo youngGirl = new UserInfo();
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }
    }

    @Test
    public void test2() {
        IUserInfo youngGirl = new OuterUserInfo();
        for (int i = 0; i < 101; i++) {
            System.out.println(youngGirl.getMobileNumber());
        }
    }
}
