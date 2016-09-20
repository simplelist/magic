package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public class RequirementGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到需求组.....");
    }

    @Override
    public void add() {
        System.out.println("客户要求增加需求.....");

    }

    @Override
    public void delete() {
        System.out.println("客户需要删除需求.....");

    }

    @Override
    public void change() {

        System.out.println("客户需要修改某一需求.....");
    }

    @Override
    public void plan() {
        System.out.println("客户要求需求变更计划.....");

    }
}
