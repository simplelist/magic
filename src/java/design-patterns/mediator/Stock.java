package mediator;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Stock extends AbstractColleague {
    private static int COMPUTER_NUMBER = 100;

    public Stock(AbstractMediator mediator) {
        super(mediator);
    }

    //库存增加
    public void increase(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER + number;
        System.out.println("库存数量为:" + COMPUTER_NUMBER);
    }

    //库存减少
    public void decrease(int number) {
        COMPUTER_NUMBER = COMPUTER_NUMBER - number;
        System.out.println("库存数量为:" + COMPUTER_NUMBER);
    }

    public int getStockNumber() {
        return COMPUTER_NUMBER;
    }

    public void clearStock() {
        System.out.println("清理存货数量为:" + COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }
}
