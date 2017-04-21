package visitor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */
public class Client {
    @Test
    public void test1() {
        IVisitor visitor = new Visitor();
        for (Employee employee : mockEmployee()) {
            employee.accept(visitor);
        }
        System.out.println("本公司的月工资总额是:" + visitor.getTotalSalary());
    }

    public List<Employee> mockEmployee() {
        List<Employee> empList = new ArrayList<Employee>();
        CommonEmployee zhangsan = new CommonEmployee();
        zhangsan.setJob("写代码,蓝领搬运工");
        zhangsan.setName("张三");
        zhangsan.setSalary(1800);
        zhangsan.setSex(Employee.MALE);
        empList.add(zhangsan);

        Manager wangwu = new Manager();
        wangwu.setName("王五");
        wangwu.setSex(Employee.MALE);
        wangwu.setSalary(18400);
        wangwu.setPerformance("业绩一般");
        empList.add(wangwu);
        return empList;
    }
}
