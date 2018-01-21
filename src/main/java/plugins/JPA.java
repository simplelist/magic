package plugins;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class JPA implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();

        PreparedStatementHandler delegate = (PreparedStatementHandler) getFieldValue(handler, "delegate");
        MappedStatement mappedStatement = (MappedStatement) getFieldValue(delegate, "mappedStatement");
        if (SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            DefaultParameterHandler parameterHandler = (DefaultParameterHandler) getFieldValue(delegate, "parameterHandler");
            BoundSql boundSql = delegate.getBoundSql();
            setSQL(boundSql, mappedStatement, parameterHandler);
        }
        return invocation.proceed();
    }

    private void setSQL(BoundSql boundSql, MappedStatement mappedStatement, DefaultParameterHandler parameterHandler) throws NoSuchFieldException {
        Field field = ReflectionUtils.findField(boundSql.getClass(), "sql");
        field.setAccessible(true);
        Class<?> resultEntity = mappedStatement.getResultMaps().get(0).getType();
        String tableName = resultEntity.getSimpleName().toLowerCase();
        String statementId = mappedStatement.getId();//selectByField
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
                    //如果包含 and 表示多个查询条件
                    if (methodName.contains("and")) {
                        String[] fields = methodName.split("and");
                        HashMap paramMap = (HashMap) parameterObject;//将参数强转为 HashMap
                        boolean first = true;
                        for (int i = 0; i < fields.length; i++) {
                            //拼接 and 语句
                            if (!first) {
                                sql.append(" and ");
                            }
                            processEquals(paramMap.get(i + ""), sql, fields[i]);
                            first = false;
                        }
                    } else {
                        //处理一个查询条件
                        processEquals(parameterObject, sql, methodName);
                    }
                }

            }
            ReflectionUtils.setField(field, boundSql, sql.toString());
        }
    }

    /**
     * 处理准确查询,拼接 SQL 语句
     *
     * @param value
     * @param sql
     * @param methodName
     */
    private void processEquals(Object value, StringBuffer sql, String methodName) {
        sql.append(methodName);
        sql.append(" = '");
        sql.append(value);
        sql.append("'");
    }

    private Object getFieldValue(StatementHandler handler, String fieldName) {
        Field field = ReflectionUtils.findField(handler.getClass(), fieldName);
        field.setAccessible(true);
        return ReflectionUtils.getField(field, handler);
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
