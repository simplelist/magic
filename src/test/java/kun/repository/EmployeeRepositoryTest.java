package kun.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kun.domain.Employee;
import com.kun.repository.EmployeeRepository;

/**
 * Created by kun on 2017/4/21.
 */
public class EmployeeRepositoryTest {


    private ApplicationContext ctx=null;
    private EmployeeRepository employeeRepository=null;

    @Before
    public void before() {
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeRepository=ctx.getBean(EmployeeRepository.class);
    }


    @Test
    public void testTemplate() {
    }

    @Test
    public void testQuery() {
        System.out.println(employeeRepository.findByName("张辽"));

    }

    @Test
    public void testFindByNameStartingWithAndAgeLessThan(){
        List<Employee> employeeList = employeeRepository.findByNameStartingWithAndAgeLessThan("张", 31);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void testFindByNameEndingWithAndAgeLessThan(){
        List<Employee> employeeList = employeeRepository.findByNameEndingWithAndAgeLessThan("远", 31);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void testFindByNameInOrAgeLessThan(){
        List<Employee> employeeList = employeeRepository.findByNameInOrAgeLessThan(Arrays.asList("张辽","张郃"), 26);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void testCreateTable() {

    }
    @Test
    public void testSave() {
        List<Employee> employees=new LinkedList<>();
        employees.add(new Employee("张郃",25));
        employees.add(new Employee("张远",25));
        employees.add(new Employee("高远",26));
        employeeRepository.save(employees);
    }


    @Test
    public void testUpdate(){
        employeeRepository.updateAgeById(1,60);
    }
    @After
    public void after() {
        ctx=null;
    }
}
