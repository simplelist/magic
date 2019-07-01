package redis;

/**
 * @Author 胡说
 * @DATETIME 2019-07-01 20:59
 */
public class DefaultLimit implements ILimit {
    @Override
    public int count() {
        return 2;
    }

    @Override
    public int second() {
        return 10;
    }

    @Override
    public String key() {
        return "interface_limit";
    }

    @Override
    public boolean increaseValueRefreshTTL() {
        return true;
    }
}
