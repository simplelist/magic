package mediator;

import java.util.Random;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Sale extends AbstractColleague {

    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    public void sellIBMComputer(int number) {
        System.out.println("销售IBM电脑" + number + "台");
        super.mediator.execute("sale.sell", number);
    }

    public int getSaleStatus() {
        Random rand = new Random(System.currentTimeMillis());
        int saleStatus = rand.nextInt(100);
        System.out.println("IBM电脑的销售情况为: " + saleStatus);
        return saleStatus;
    }

    public void offSale() {
        super.mediator.execute("sale.offsell");
    }
}
