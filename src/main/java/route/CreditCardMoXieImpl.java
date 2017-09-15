package route;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: zdan91.route.CreditCardMoJieImpl.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 13/09/2017 09:54
 * Description:
 * History:
 */
@Component
public class CreditCardMoXieImpl extends AbstractRoute {
    @Override
    public Object process(Map<String, Object> paraMap) {
        System.out.println("MoXieImpl");
        nextProcess(paraMap);
        return null;
    }

}
