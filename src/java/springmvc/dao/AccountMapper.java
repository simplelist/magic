package springmvc.dao;

import springmvc.dto.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作数据库的Dao层
 * Created by Administrator on 2016/9/28.
 */
public interface AccountMapper {
    @Select("select * from account where id=#{id}")
    Account findOne(Integer id);

    @Select("select * from account")
    List<Account> findAll();
}
