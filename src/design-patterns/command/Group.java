package command;

/**
 * Created by simplelist on 2016/9/20.
 */
public abstract class Group {
    //甲方乙方分开办公,如果你要和某个组讨论,你首先要找到这个组
    public abstract void find();

    //被要求增加功能
    public abstract void add();

    //删除功能
    public abstract void delete();

    //修改功能
    public abstract void change();

    //被要求给出变更计划
    public abstract void plan();
}
