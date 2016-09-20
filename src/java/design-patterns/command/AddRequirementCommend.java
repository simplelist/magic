package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public class AddRequirementCommend extends Command {
    @Override
    public void execute() {
        super.rg.find();
        super.rg.add();
        super.pg.add();
        super.cp.add();
        super.rg.plan();
    }

}
