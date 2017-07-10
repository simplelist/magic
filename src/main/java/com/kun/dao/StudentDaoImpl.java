package com.kun.dao;

import com.kun.domain.Student;
import com.kun.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kun on 2017/4/21.
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> query() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql="select id, name, age from student";
        List<Student> list=new LinkedList<>();
        try {
            connection=JDBCUtil.getConnection();
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();

            while (resultSet.next()){

                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");

                Student student=new Student(id,name,age);
                list.add(student);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(resultSet,statement,connection);
        }
        return list;
    }

    @Override
    public void save(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql=" insert into student(name,age) values (?,?);";
        try {
            connection=JDBCUtil.getConnection();
            statement=connection.prepareStatement(sql);
            statement.setString(1,student.getName());
            statement.setInt(2,student.getAge());
            statement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(resultSet,statement,connection);
        }
    }
}
