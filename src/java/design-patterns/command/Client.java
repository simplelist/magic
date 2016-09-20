package command;

import org.junit.Test;

/**
 * Created by simplelist on 2016/9/20.
 */
public class Client {
    @Test
    public void testAddRequirementCommand() {
        Invoker zhangsan = new Invoker();
        Command command = new AddRequirementCommend();
        zhangsan.setCommand(command);
        zhangsan.action();
    }

    @Test
    public void testDeletePageCommand() {
        Invoker zhangsan = new Invoker();
        Command command = new DeletePageCommand();
        zhangsan.setCommand(command);
        zhangsan.action();
    }
}
