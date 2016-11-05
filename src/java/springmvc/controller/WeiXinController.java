package springmvc.controller;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.service.WeiXinService;

/**
 * Created by simplelist on 2016/11/5.
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController extends WeixinControllerSupport {
    private static final String token = "kun321";
    @Autowired
    private WeiXinService weiXinService;

    @Override
    protected String getToken() {
        return token;
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        String result = "︿(￣︶￣)︿";
        if (content.equals("1")) {
            result = "小时候把老爷子耍了多年的文玩核桃砸开了，我操，竟然没有仁。第二天一早一瘸一拐地去上学了";
        } else {
            result = weiXinService.fetchContent(content);
        }
        return new TextMsg(result);
    }
}
