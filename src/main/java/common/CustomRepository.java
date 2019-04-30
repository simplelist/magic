package common;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基础(还需在XML文件里，有对应的SQL语句)
 */
public interface CustomRepository<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T> {
}
