package bridge;

/**
 * Created by Administrator on 2016/10/12.
 */
public class HouseCorp extends Corp {


    public HouseCorp(House house) {
        super(house);
    }

    @Override
    public void makeMoney() {

        super.makeMoney();
        System.out.println("房地产公司赚大钱了......");
    }
}
