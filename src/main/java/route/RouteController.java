package route;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: zdan91.route.RouteController.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 12/09/2017 18:39
 * Description:
 * History:
 */
@Component
public class RouteController {
	public static void main(String args[]){

	}

    private final Logger log = LoggerFactory.getLogger(RouteController.class);
    private List<AbstractRoute> abstractRoutes;
    private AbstractRoute currentInstance;

    private void preHandle(RouteApiKey apiKey) {
        /**
         * 1. 从redis获取路由配置,并按照权重排序
         * 2. 调用process方法,由实现类具体去发送异步请求,得到回调,获取结果,解析,得到结果.
         * 3. 如果获取数据失败尝试下一个实现.
         */
        abstractRoutes = boxChain(apiKey.getKey());
    }

	/*从第一个实现开始*/
	public Object process(RouteApiKey apiKey, Map<String, Object> paraMap) {
		if (CollectionUtil.isEmpty(abstractRoutes)) {
			preHandle(apiKey);
			currentInstance = abstractRoutes.get(0);
		}
		return currentInstance.process(paraMap);
	}

    /*以下是构建责任链的方法*/
    private List<AbstractRoute> boxChain(String apiKey) {
        log.info(apiKey + "进入Route");
        SysConfig config = getConfig(apiKey);
        JSONArray routesArray = JSONUtil.parseObj(config.getSysValue()).getJSONArray("routes");
        List<RouteConfig> routeConfigs = routesArray.stream().
                map(route -> JSONUtil.toBean(JSONUtil.parseObj(route), RouteConfig.class)).
                collect(Collectors.toList());
        //按照权重排序
        Collections.sort(routeConfigs, Comparator.comparing(RouteConfig::getWeight).reversed());
        log.debug("获取到路由:");
        routeConfigs.stream().forEach(s -> log.debug(s.toString()));
        List<AbstractRoute> routes = routeConfigs.stream().
                map(s -> generateRouteInstance(s)).
                collect(Collectors.toList());
        processChain(routes);
        return routes;
    }

    private void processChain(List<AbstractRoute> routes) {
        for (int i = 0; i < routes.size() - 1; i++) {
            routes.get(i).setNextHandle(routes.get(i + 1));
        }
    }

    private AbstractRoute generateRouteInstance(RouteConfig config) {
        try {
        	//不能通过反射获取实例,反射得到的没有相关的依赖
            return SpringContextUtils.getTargetObject(config.getImplClazz(), AbstractRoute.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private SysConfig getConfig(String apiKey){
    	//这里可以将配置放在数据库或者redis缓存中
    	return new SysConfig("{\"routes\":[{\"implClazz\":\"creditCardLiMuImpl\",\"weight\":7},{\"implClazz\":\"creditCardService\",\"weight\":9}]}");
	}

}
