package Memento;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/7.
 */
public class Client {
    @Test
    public void test1() {
        Boy boy = new Boy();

        boy.setState("心情很棒");

        System.out.println("============男孩现在的心情===============");
        System.out.println(boy.getState());

        Boy backup = new Boy();

        backup.setState(boy.getState());
        boy.changeState();
        System.out.println("\n================男孩追女孩子后的心情============");
        System.out.println(boy.getState());

        boy.setState(backup.getState());

        System.out.println("=============男孩回复后的心情======================");
        System.out.println(boy.getState());
    }

    @Test
    public void test2() {
        Boy boy = new Boy();

        boy.setState("心情很棒");

        System.out.println("============男孩现在的心情===============");
        System.out.println(boy.getState());

        Memento mem = boy.createMemento();
        boy.changeState();
        System.out.println("\n================男孩追女孩子后的心情============");
        System.out.println(boy.getState());

        boy.restoreMemento(mem);

        System.out.println("=============男孩回复后的心情======================");
        System.out.println(boy.getState());
    }

    @Test
    public void test3() {
        //定义发起人
        Boy boy = new Boy();
        //定义出备忘录管理员
        Caretaker caretaker = new Caretaker();
        boy.setState("心情很棒");

        System.out.println("============男孩现在的心情===============");
        System.out.println(boy.getState());

        //创建备忘
        caretaker.setMemento(boy.createMemento());
        boy.changeState();
        System.out.println("\n================男孩追女孩子后的心情============");
        System.out.println(boy.getState());

        //回复备忘
        boy.restoreMemento(caretaker.getMemento());

        System.out.println("=============男孩回复后的心情======================");
        System.out.println(boy.getState());
    }
}
