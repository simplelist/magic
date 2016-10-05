package iterator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/5.
 */
public class ProjectIterator implements IProjectIterator {
    private ArrayList<IProject> projectList = new ArrayList<IProject>();
    private int currentItem = 0;

    public ProjectIterator(ArrayList<IProject> projectList) {
        this.projectList = projectList;
    }

    public boolean hasNext() {
        boolean b = true;
        if (this.currentItem >= projectList.size() || this.projectList.get(this.currentItem) == null) b = false;
        return b;
    }

    public IProject next() {
        return this.projectList.get(this.currentItem++);
    }

    public void remove() {

    }
}
