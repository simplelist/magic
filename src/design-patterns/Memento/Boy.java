package Memento;

/**
 * Created by Administrator on 2016/10/7.
 */
public class Boy {
    //内部状态
    private String state = "";

    public void changeState() {
        this.state = "心情可能不太好";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //创建一个备忘录
    public Memento createMemento() {
        return new Memento(this.state);
    }

    //回复一个备忘录
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}
