package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public class DeletePageCommand extends Command {
    @Override
    public void execute() {
        super.pg.find();
        super.pg.delete();
        super.pg.plan();
    }

}
