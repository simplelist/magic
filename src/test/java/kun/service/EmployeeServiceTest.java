package kun.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.kun.domain.Employee;
import com.kun.repository.EmployeeRepository;
import com.kun.service.EmployeeService;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
public class EmployeeServiceTest {



    private ApplicationContext ctx=null;
    private EmployeeService employeeService=null;
    private EmployeeRepository employeeRepository=null;

    @Before
    public void before() {
        ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeService=ctx.getBean(EmployeeService.class);
        employeeRepository=ctx.getBean(EmployeeRepository.class);
    }
    @After
    public void after() {
        ctx=null;
    }

    @Test
    public void testUpdate(){
        employeeService.updateAgeById(1,61);
    }
    @Test
    public void testSave(){
        List<Employee> employees=new LinkedList<>();
        Employee employee=null;
        for (int i=0;i<100;i++) {
            employee=new Employee("张"+i,100-i);
            employees.add(employee);
        }
        employeeService.save(employees);
    }

    @Test
    public void testPage(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Specification<Employee> specification=new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path age = root.get("age");
                return cb.lt(age,50);
            }
        };
        Pageable pageable=new PageRequest(0,10,sort);
        Page<Employee> all = employeeRepository.findAll(specification,pageable);
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        System.out.println(all.getContent());

    }
}
