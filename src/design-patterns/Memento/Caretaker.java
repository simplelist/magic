package Memento;

/**
 * 备忘录管理员角色
 * Created by Administrator on 2016/10/7.
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
