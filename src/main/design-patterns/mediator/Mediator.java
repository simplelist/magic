package mediator;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Mediator extends AbstractMediator {
    @Override
    public void execute(String str, Object... objects) {
        if (str.equalsIgnoreCase("purchase.bug")) {
            this.buyComputer((Integer) objects[0]);
        } else if (str.equalsIgnoreCase("sale.sell")) {
            this.sellComputer((Integer) objects[0]);
        } else if (str.equalsIgnoreCase("sale.offsell")) {
            this.offSell();
        } else if (str.equalsIgnoreCase("stock.clear")) {
            this.clearStock();
        }
    }

    public void buyComputer(int number) {
        int saleStatus = super.sale.getSaleStatus();
        //销售情况良好
        if (saleStatus > 80) {
            System.out.println("采购IBM电脑" + number + "台");
        } else {
            //销售情况堪忧哇
            int buyNumber = number / 2;
            System.out.println("采购IBM电脑" + buyNumber + "台");
        }
    }

    public void sellComputer(int number) {
        if (super.stock.getStockNumber() < number) {
            super.purchase.buyIBMComputer(number);
        }
        super.stock.increase(number);
    }

    public void offSell() {
        System.out.println("折价销售IBM电脑" + super.stock.getStockNumber() + "台");
    }

    public void clearStock() {
        super.sale.offSale();
        super.purchase.refuseBuyIBM();
    }
}
