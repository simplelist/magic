package com.kun.dao;

import com.kun.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kun on 2017/4/21.
 */
public class StudentDAOSpringJdbcImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Student> query() {
        String sql="select id,name,age from student limit 0,10";
        List<Student> list=new LinkedList<>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                        int id=resultSet.getInt("id");
                        int age=resultSet.getInt("age");
                        String name=resultSet.getString("name");

                        Student student=new Student(id,name,age);
                        list.add(student);
            }
        });
        return list;
    }

    @Override
    public void save(Student student) {
        String sql= "insert into student(name,age) values (?,?)";

        jdbcTemplate.update(sql,student.getName(),student.getAge());

    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
