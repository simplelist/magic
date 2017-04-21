package decorator;

/**
 * Created by Administrator on 2016/10/1.
 */
public class SugarFouthGradeSchoolReport extends FouthGradeSchoolReport {
    public void reportHighScore() {
        System.out.println("这次考试最高成绩语文89,数学90");
    }

    public void reportSort() {
        System.out.println("我是排名36");
    }

    @Override
    public void report() {
        this.reportHighScore();
        super.report();
        this.reportSort();
    }
}
