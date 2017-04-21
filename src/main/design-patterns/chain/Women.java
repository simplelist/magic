package chain;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Women implements IWomen {
    /*通过一个状态来描述女人的状态
    * 1 未出嫁
    * 2 出嫁
    * 3 丧偶
    * */

    private int type = 0;
    private String request = "";

    public Women(int type, String request) {
        this.type = type;
        this.request = request;
    }

    public int getType() {
        return this.type;
    }

    public String getRequest() {
        return this.request;
    }
}
