package plugins;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class JPA implements Interceptor {


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

        if (SqlCommandType.SELECT.equals(statement.getSqlCommandType())) {

            setSQL(boundSql, statement, (DefaultParameterHandler) SystemMetaObject.forObject(preparedStatementHandler).getValue("parameterHandler"));
        }
        return invocation.proceed();
    }

    private void setSQL(BoundSql boundSql, MappedStatement mappedStatement, DefaultParameterHandler parameterHandler) throws NoSuchFieldException {
        Class<?> resultEntity = mappedStatement.getResultMaps().get(0).getType();
        String tableName = resultEntity.getSimpleName().toLowerCase();
        String statementId = mappedStatement.getId();//selectById
        String boundSqlStr = boundSql.getSql();
        if (boundSqlStr == null || boundSqlStr.equals("")) {
            StringBuffer sql = new StringBuffer("select * from ");
            //todo 驼峰下划线处理
            sql.append(tableName);
            if (statementId.contains("By")) {
                String methodName = statementId.substring(statementId.lastIndexOf("By") + 2).toLowerCase();
                sql.append(" where ");
                boolean like = statementId.contains("Like");
                Object parameterObject = parameterHandler.getParameterObject();
                if (like) {//如果是模糊查询 like
                    sql.append(methodName.substring(0, methodName.lastIndexOf("like")));
                    sql.append(" like '%");
                    sql.append(parameterObject);
                    sql.append("%'");
                } else {//精确查询 =
                    if (methodName.contains("and")) {
                        //如果包含 and 表示多个查询条件
                        String[] fields = methodName.split("and");
                        HashMap paramMap = (HashMap) parameterObject;
                        boolean first = true;
                        for (int i = 0; i < fields.length; i++) {
                            if (!first) {
                                sql.append(" and ");
                            }
                            processEquals(paramMap.get(i + ""), sql, fields[i]);
                            first = false;

                        }
                    } else {
                        processEquals(parameterObject, sql, methodName);
                    }
                }

            }
            SystemMetaObject.forObject(boundSql).setValue("sql", sql.toString());
        }


    }

    private void processEquals(Object value, StringBuffer sql, String methodName) {
        sql.append(methodName);
        sql.append(" = '");
        sql.append(value);
        sql.append("'");
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
