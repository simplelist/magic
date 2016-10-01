package decorator;

/**
 * Created by Administrator on 2016/10/1.
 */
public class FouthGradeSchoolReport extends SchoolReport {
    public void report() {
        System.out.println("尊敬的XXX家长");
        System.out.println("..............");
        System.out.println("语文78,数学85");
        System.out.println("..............");
    }

    public void sign(String name) {
        System.out.println(".......家长签名为:" + name + ".......");
    }
}
