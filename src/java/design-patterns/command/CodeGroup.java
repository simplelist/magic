package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public class CodeGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到代码组.....");
    }

    @Override
    public void add() {
        System.out.println("客户要求增加功能.....");

    }

    @Override
    public void delete() {
        System.out.println("客户需要删除功能.....");

    }

    @Override
    public void change() {

        System.out.println("客户需要修改某一功能.....");
    }

    @Override
    public void plan() {
        System.out.println("客户要求功能变更计划.....");

    }
}
