package mediator;

import org.junit.Test;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Client {

    @Test
    public void testSale() {
        AbstractMediator mediator = new Mediator();
        System.out.println("-----------采购人员采购电脑--------------");
        Purchase purchase = new Purchase(mediator);
        purchase.buyIBMComputer(100);
        System.out.println("----------\n销售人员销售电脑--------------");
        Sale sale = new Sale(mediator);
        sale.sellIBMComputer(1);
        System.out.println("-----------\n库房管理人员清仓处理--------------");
        Stock stock = new Stock(mediator);
        stock.clearStock();
    }
}
