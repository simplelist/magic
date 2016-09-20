package chain;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Father extends Handler {


    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }


    @Override
    protected void response(IWomen women) {
        System.out.println("来自女儿的请示是:" + women.getRequest());
        System.out.println("父亲的答复是:同意");
    }
}
