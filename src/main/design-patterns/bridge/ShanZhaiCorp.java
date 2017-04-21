package bridge;

/**
 * Created by Administrator on 2016/10/12.
 */
public class ShanZhaiCorp extends Corp {
    public ShanZhaiCorp(Product produce) {
        super(produce);
    }

    public void makeMoney() {
        super.makeMoney();
        System.out.println("赚钱呀...........");
    }
}
