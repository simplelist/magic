package iterator;

/**
 * Created by Administrator on 2016/10/5.
 */
public interface IProject {
    public void add(String name, int num, int cost);

    public String getProjectInfo();

    public IProjectIterator iterator();
}
