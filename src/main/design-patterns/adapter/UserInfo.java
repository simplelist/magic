package adapter;

/**
 * Created by Administrator on 2016/10/4.
 */
public class UserInfo implements IUserInfo {
    public String getUserName() {
        System.out.println("这是员工的姓名");

        return null;
    }

    public String getHomeAddress() {
        System.out.println("这是员工的姓名");
        return null;
    }

    public String getMobileNumber() {
        System.out.println("这是手机号码");
        return null;
    }

    public String getOfficeTelNumber() {
        System.out.println("公司电话是.......");
        return null;
    }

    public String getJobPosition() {
        System.out.println("这个人的职位是BOSS ");
        return null;
    }

    public String getHomeTelNumber() {
        System.out.println("家庭号码...........");
        return null;
    }
}
