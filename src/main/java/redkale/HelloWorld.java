package redkale;

import org.redkale.service.Service;

/**
 * Created by Administrator on 2017/4/4.
 */
public class HelloWorld implements Service {
    private String str="Hello,world";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
