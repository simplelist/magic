package weixin;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.junit.Test;

/**
 * Created by kun on 2017/4/13.
 */
public class TestWeChat {

    @Test
    public void test() {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("wxc1cc230b1d7f9026"); // 设置微信公众号的appid
        config.setSecret("4c4584f2b85a9cc13d0e028e0369cbad"); // 设置微信公众号的app corpSecret

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

        try {
            WxMpUser wxMpUser=wxService.getUserService().userInfo("o_BJDwJRrBiJqrDCHXZwMQ688QxA");
            System.out.println(wxMpUser);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
// 用户的openid在下面地址获得
// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=用户管理&form=获取关注者列表接口%20/user/get
//        String openid = "o_BJDwJRrBiJqrDCHXZwMQ688QxA";
//        WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(openid).content("Hello World").build();
//        try {
//            wxService.getKefuService().sendKefuMessage(message);
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
    }
}
