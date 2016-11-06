package springmvc.service.impl;

import org.springframework.stereotype.Service;
import spider.Qiubai;
import springmvc.service.WeiXinService;

/**
 * Created by simplelist on 2016/11/5.
 */
@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService {
    @Override
    public String fetchJoke() {
        return Qiubai.joke();
    }

    @Override
    public String fetchEnglish(String msg) {
        return Qiubai.english();
    }


}
