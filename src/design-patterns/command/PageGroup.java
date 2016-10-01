package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public class PageGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到美工组.....");
    }

    @Override
    public void add() {
        System.out.println("客户要求增加页面.....");

    }

    @Override
    public void delete() {
        System.out.println("客户需要删除页面.....");

    }

    @Override
    public void change() {

        System.out.println("客户需要修改某一页面.....");
    }

    @Override
    public void plan() {
        System.out.println("客户要求页面变更计划.....");

    }
}
