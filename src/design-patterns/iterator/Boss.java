package iterator;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/5.
 */
public class Boss {
    @Test
    public void test1() {
        IProject project = new Project();
        project.add("星球大战项目DDGWREUH", 10, 10000);
        project.add("扭转时空项目", 100, 2300);
        project.add("超人改造项目", 1850, 2342);

        for (int i = 4; i < 104; i++) {
            project.add("第" + i + "个项目", i * 5, i * 500);
        }
        IProjectIterator proIter = project.iterator();
        while (proIter.hasNext()) {
            IProject p = (IProject) proIter.next();
            System.out.println(p.getProjectInfo());
        }
    }
}
