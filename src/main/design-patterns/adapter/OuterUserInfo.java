package adapter;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
public class OuterUserInfo extends OuterUser implements IUserInfo {
    private Map baseInfo = super.getUserBaseInfo();
    private Map homeInfo = super.getUserHomeInfo();
    private Map officeInfo = super.getUserOfficeInfo();


    public String getUserName() {
        return MapUtils.getString(baseInfo, "userName");
    }

    public String getHomeAddress() {
        return MapUtils.getString(homeInfo, "homeAddress");
    }

    public String getMobileNumber() {
        return MapUtils.getString(baseInfo, "mobileNumber");
    }

    public String getOfficeTelNumber() {
        return MapUtils.getString(officeInfo, "officeTelNumber");
    }

    public String getJobPosition() {
        return MapUtils.getString(officeInfo, "jobPosition");
    }

    public String getHomeTelNumber() {
        return MapUtils.getString(homeInfo, "homeTelNumber");
    }
}
