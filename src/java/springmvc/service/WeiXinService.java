package springmvc.service;

import org.springframework.stereotype.Service;

/**
 * Created by simplelist on 2016/11/5.
 */
public interface WeiXinService {
    /**
     * 讲个笑话
     *
     * @return
     */
    String fetchJoke();

    /**
     * @param msg
     * @return
     */
    String fetchEnglish(String msg);

    String searchPan(String content);
}
