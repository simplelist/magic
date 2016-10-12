package bridge;

/**
 * Created by Administrator on 2016/10/12.
 */
public class ClothesCorp extends Corp {
    public ClothesCorp(Product produce) {
        super(produce);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("服装公司赚小钱");
    }
}
