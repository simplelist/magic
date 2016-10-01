package decorator;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/1.
 */
public class Father {

    @Test
    public void a() {
        SchoolReport sr = new FouthGradeSchoolReport();
        sr.report();
    }

    @Test
    public void b() {
        SchoolReport sr = new SugarFouthGradeSchoolReport();
        sr.report();
        sr.sign("张老三");
    }

    @Test
    public void c() {
        SchoolReport sr = new FouthGradeSchoolReport();
        sr = new HighScoreDecorator(sr);
        sr = new SortDecorator(sr);
        sr.report();
        sr.sign("张老三");
    }
}
