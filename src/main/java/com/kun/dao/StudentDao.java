package com.kun.dao;

import java.util.List;

import com.kun.domain.Student;

/**
 * Created by kun on 2017/4/21.
 */
public interface StudentDao {
    List<Student> query();

    void save(Student student);
}
