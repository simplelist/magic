package adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/4.
 */
public class OuterUser implements IOutUser {
    public Map getUserBaseInfo() {
        HashMap baseInfoMap = new HashMap();
        baseInfoMap.put("userName", "这个员工叫混世魔王.....");
        baseInfoMap.put("mobileNumber", "这个员工电话是.....");
        return baseInfoMap;
    }

    public Map getUserOfficeInfo() {
        HashMap homeInfo = new HashMap();
        homeInfo.put("homeTelNumber", "员工的家庭电话是.....");
        homeInfo.put("homeAddress", "员工家庭地址是.....");
        return homeInfo;
    }

    public Map getUserHomeInfo() {
        HashMap officeInfo = new HashMap();
        officeInfo.put("jobPosition", "这个人的职位是BOSS...");
        officeInfo.put("officeTelNumber", "员工办公室电话是..");
        return officeInfo;
    }
}
