package chain;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Husband extends Handler {
    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("来自妻子的请示是:" + women.getRequest());
        System.out.println("丈夫的答复是:同意");
    }
}
