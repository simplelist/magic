package decorator;

/**
 * Created by Administrator on 2016/10/1.
 */
public class SortDecorator extends Decorator {
    public SortDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportSort() {
        System.out.println("我是排名36");
    }

    @Override
    public void report() {
        this.reportSort();
        super.report();
    }
}
