package visitor;

/**
 * Created by Administrator on 2016/10/7.
 */
public class Visitor implements IVisitor {
    //经理的工资系数是5
    private final static int MANAGER_COEFFICIENT = 5;
    //普通员工的工资系数是2
    private final static int COMMONEMPLOYEE_COEFFICIENT = 2;
    private int commonTotalSalary = 0;
    private int managerTotalSalary = 0;

    public void visit(CommonEmployee commonEmployee) {
        System.out.println(this.getCommonEemployee(commonEmployee));
    }

    public void visit(Manager manager) {
        System.out.println(this.getManagerInfo(manager));
    }


    //组装出基本信息
    private String getBasicInfo(Employee employee) {
        String info = "姓名:" + employee.getName() + "\t";
        info += "性别:" + (employee.getSex() == Employee.FAMALE ? "女" : "男") + "\t";
        info += "薪水:" + employee.getSalary() + "\t";
        return info;
    }

    //组装出经理的信息
    private String getManagerInfo(Manager manager) {
        String basicInfo = this.getBasicInfo(manager);
        String otherInfo = "业绩:" + manager.getPerformance() + "\t";
        calMangerSalary(manager.getSalary());
        return basicInfo + otherInfo;
    }

    //组装出普通员工信息
    private String getCommonEemployee(CommonEmployee commonEmployee) {
        String basicInfo = this.getBasicInfo(commonEmployee);
        String otherInfo = "工作:" + commonEmployee.getJob() + "\t";
        calCommonSalary(commonEmployee.getSalary());
        return basicInfo + otherInfo;
    }

    private void calMangerSalary(int salary) {
        this.managerTotalSalary = this.managerTotalSalary + salary * MANAGER_COEFFICIENT;
    }

    private void calCommonSalary(int salary) {
        this.commonTotalSalary = this.commonTotalSalary + salary * COMMONEMPLOYEE_COEFFICIENT;
    }

    public int getTotalSalary() {
        return this.commonTotalSalary + this.managerTotalSalary;
    }
}
