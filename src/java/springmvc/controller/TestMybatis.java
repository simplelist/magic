package springmvc.controller;

import springmvc.dto.Account;
import springmvc.dao.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 测试Mybatis
 * Created by Administrator on 2016/9/28.
 */
public class TestMybatis {
    private SqlSession sqlSession;
    private AccountMapper mapper;

    @Before
    public void before() {
        String resource = "conf.xml";
        InputStream is = TestMybatis.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sessionFactory.openSession();
        mapper = sqlSession.getMapper(AccountMapper.class);
    }
    /*用XML方式*/
//    @Test
//    public void testSelectOne(){
//        String statement = "springmvc.dto.mapping.accountMapper.getAccount";//映射sql的标识字符串
//        Account account = sqlSession.selectOne(statement,2);
//        System.out.println(account);
//    }
    @Test
    public void testSelectAll(){
        String statement = "springmvc.dto.mapping.accountMapper.getAll";//映射sql的标识字符串
        List<Account> accounts = sqlSession.selectList(statement);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /*用注解方式*/
    @Test
    public void testMapperSelectOne() {

        Account account = mapper.findOne(1);
        System.out.println(account);
    }

    @Test
    public void testMapperSelectAll() {
        List<Account> accounts = mapper.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @After
    public void after() {
        sqlSession.close();
    }
}
