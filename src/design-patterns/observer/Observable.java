package observer;

/**
 * Created by Administrator on 2016/10/5.
 */
public interface Observable {
    public void add(Observer observer);

    public void remove(Observer observer);

    public void notify(String context);

}
