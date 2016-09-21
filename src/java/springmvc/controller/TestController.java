package controller;

import com.xiaoleilu.hutool.lang.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackshi on 16/9/20.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/add.do")
    @ResponseBody
    public Map<String,Object> add() {
        Map<String,Object> result=new HashMap<String, Object>();
        System.out.println("this is add method");
        result.put("now",new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        return result;
    }
}
