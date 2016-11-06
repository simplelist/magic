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
    private String result = "请输入指令:\n0获取菜单\n1讲个笑话\n2每日一句";
    @Autowired
    private WeiXinService weiXinService;

    @Override
    protected String getToken() {
        return token;
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        if (content.equals("1")) {
            result = weiXinService.fetchJoke();
        } else if (content.equals("2")) {
            result = weiXinService.fetchEnglish(content);
        }
        return new TextMsg(result);
    }
}
