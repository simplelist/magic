package route;

import java.util.Map;

/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: zdan91.route.AbstractRoute.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 12/09/2017 18:14
 * Description: 路由策略实现的抽象父类
 * History:
 */
public abstract class AbstractRoute {


    /*指定下一个处理节点,如果当前处理失败则调用下一个节点去处理*/
    private AbstractRoute next;

    /*在组装的时候设置下一个处理节点*/
    public void setNextHandle(AbstractRoute nextHandle) {
        this.next = nextHandle;
    }

    /**
     * 如果直接在子类中process方法中调用下一个节点的process方法,在最后一个节点会出现空指针异常
     *
     * @param paraMap
     * @return
     */
    public Object nextProcess(Map<String, Object> paraMap) {
        if (next != null) {
            next.process(paraMap);
        }
        return null;
    }

    public abstract Object process(Map<String, Object> parameterMap) ;





}
