package springmvc.controller;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.service.WeiXinService;

import java.util.ArrayList;
import java.util.List;

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
        } else if (content.startsWith("3")) {
            result = weiXinService.searchPan(content);
        }
        return new TextMsg(result);
    }

    @Test
    public void statwewe() {
        createMenu(new ApiConfig("wxeac78b9599e833ad", "50426f498f5f09346a9427b7f7bf5915"));
    }

    /**
     * 创建菜单
     *
     * @param config API配置
     */
    private void createMenu(ApiConfig config) {
        MenuAPI menuAPI = new MenuAPI(config);

        //先删除之前的菜单
        menuAPI.deleteMenu();
        Menu request = new Menu();
        //准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.CLICK);
        main1.setKey("main1");
        main1.setName("测试");
        //准备子菜单
        MenuButton sub1 = new MenuButton();
        sub1.setKey("sub1");
        sub1.setName("授权");
        sub1.setType(MenuType.VIEW);
        MenuButton sub2 = new MenuButton();
        sub2.setKey("sub2");
        sub2.setName("点击");
        sub2.setType(MenuType.CLICK);


        List<MenuButton> list = new ArrayList<MenuButton>();
        list.add(sub1);
        list.add(sub2);
        //将子菜单放入主菜单里
        main1.setSubButton(list);

        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        //将主菜单加入请求对象
        request.setButton(mainList);
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        System.out.println(resultType);
    }
}
