package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public abstract class Command {
    protected RequirementGroup rg = new RequirementGroup();
    protected PageGroup pg = new PageGroup();
    protected CodeGroup cp = new CodeGroup();

    public abstract void execute();

}
