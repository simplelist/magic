package route;

/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: zdan91.route.RouteApiKey.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 13/09/2017 14:24
 * Description:
 * History:
 */
public enum RouteApiKey {
    CREDIT_CARD("route_config_credit_card");
    private String key;

    RouteApiKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
