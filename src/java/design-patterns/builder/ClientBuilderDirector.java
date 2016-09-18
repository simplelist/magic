package builder;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by simplelist on 2016/9/18.
 */
public class ClientBuilderDirector {
    @Test
    public void testModel() {
        Director director = new Director();
        System.out.println("-------------------------A------------------");
        director.getABenzModel().run();
        System.out.println("-------------------------B------------------");
        director.getBBenzModel().run();

        System.out.println("-------------------------C------------------");
        director.getCBMWModel().run();
        System.out.println("-------------------------D------------------");
        director.getDBMWModel().run();
    }

}
