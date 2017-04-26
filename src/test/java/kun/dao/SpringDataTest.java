package kun.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kun on 2017/4/21.
 */
public class SpringDataTest {
    private ApplicationContext ctx = null;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    @Test
    public void testTemplate() {
    }

    @Test
    public void testQuery() {

    }


    @Test
    public void testCreateTable() {

    }

    @Test
    public void testSave() {

    }

    @After
    public void after() {
        ctx = null;
    }
}
