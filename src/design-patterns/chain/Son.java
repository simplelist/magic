package chain;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Son extends Handler {
    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("来自母亲的请示是:" + women.getRequest());
        System.out.println("儿子的答复是:同意");
    }
}
