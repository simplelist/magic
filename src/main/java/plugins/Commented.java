package plugins;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Properties;

/**
 * 给 SQL 语句加上注释:该 SQL 语句的 ID,com.xx.xxMapper.selectByXX.
 * 该插件需在其他插件加载之后进行加载,插件的执行顺序:先配置的后执行,因为先配置的被包装的更深
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class Commented implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement;
        PreparedStatementHandler preparedStatementHandler;
        BoundSql boundSql;
        Object target = invocation.getTarget();

        //多个插件
        if (Proxy.isProxyClass(target.getClass())) {
            Plugin h = (Plugin) SystemMetaObject.forObject(target).getValue("h");
            RoutingStatementHandler handler = (RoutingStatementHandler) SystemMetaObject.forObject(h).getValue("target");
            preparedStatementHandler = (PreparedStatementHandler) SystemMetaObject.forObject(handler).getValue("delegate");
        } else {
            //单个插件
            preparedStatementHandler = (PreparedStatementHandler) SystemMetaObject.forObject(target).getValue("delegate");
        }

        statement = (MappedStatement) SystemMetaObject.forObject(preparedStatementHandler).getValue("mappedStatement");
        boundSql = (BoundSql) SystemMetaObject.forObject(preparedStatementHandler).getValue("boundSql");
        setSQL(boundSql, statement);
        return invocation.proceed();
    }


    private void setSQL(BoundSql boundSql, MappedStatement mappedStatement) throws NoSuchFieldException {
        StringBuffer sql = new StringBuffer("/** ");
        String sqlStr = (String) SystemMetaObject.forObject(boundSql).getValue("sql");
        String commented = mappedStatement.getId();
        sql.append(commented).append(" **/ ").append(sqlStr);
        SystemMetaObject.forObject(boundSql).setValue("sql", sql.toString());

    }

    @Override
    public Object plugin(Object target) {
        //判断是否是本拦截器需要拦截的接口类型，如果是增加代理
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        //如果不是返回源对象。
        else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
