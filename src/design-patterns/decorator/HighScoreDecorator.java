package decorator;

/**
 * Created by Administrator on 2016/10/1.
 */
public class HighScoreDecorator extends Decorator {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHighScore() {
        System.out.println("这次考试最高成绩语文89,数学90");
    }

    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}
