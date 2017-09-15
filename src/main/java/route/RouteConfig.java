package route;


/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: zdan91.vo.RouteConfig.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 12/09/2017 11:37
 * Description: 封装路由配置
 * History:
 */
public class RouteConfig {
    private String implClazz;
    private int weight = 5;

	public String getImplClazz() {
		return implClazz;
	}
	public void setImplClazz(String implClazz) {
		this.implClazz = implClazz;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
