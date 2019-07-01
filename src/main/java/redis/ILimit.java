package redis;

/**
 * @Author 胡说
 * @DATETIME 2019-07-01 20:57
 */
public interface ILimit {
    int count();

    int second();

    String key();

    boolean increaseValueRefreshTTL();
}
