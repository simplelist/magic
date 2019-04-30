package flowable;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AssigneeResponseVO {
    @ApiModelProperty(value = "工序名字")
    private String name;
    @ApiModelProperty(value = "工序代理人集合")
    private List<String> assigneeList;

    @ApiModelProperty("工序代理人姓名集合")
    private List<String> assigneeListName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(List<String> assigneeList) {
        this.assigneeList = assigneeList;
    }

    public List<String> getAssigneeListName() {
        return assigneeListName;
    }

    public void setAssigneeListName(List<String> assigneeListName) {
        this.assigneeListName = assigneeListName;
    }
}
