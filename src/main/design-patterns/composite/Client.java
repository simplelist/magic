package composite;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Client {
    public static void main(String[] args) {
        //创建一个根节点
        Composite root = new Composite();
        root.doSomething();

        //创建一个树枝构件
        Composite branch = new Composite();
        Leaf leaf = new Leaf();

        root.add(branch);
        branch.add(leaf);
        display(root);
    }

    public static void display(Composite root) {
        for (Component c : root.getChildren()) {
            if (c instanceof Leaf) {
                //叶子节点
                c.doSomething();
            } else {
                //树枝节点
                display((Composite) c);
            }
        }
    }
}
