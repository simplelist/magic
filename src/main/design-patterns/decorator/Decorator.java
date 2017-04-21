package decorator;

/**
 * Created by Administrator on 2016/10/1.
 */
public class Decorator extends SchoolReport {
    private SchoolReport sr;

    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }


    public void report() {
        this.sr.report();
    }

    public void sign(String name) {
        this.sr.sign(name);
    }
}
