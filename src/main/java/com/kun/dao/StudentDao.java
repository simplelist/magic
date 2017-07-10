package com.kun.dao;

import com.kun.domain.Student;

import java.util.List;

/**
 * Created by kun on 2017/4/21.
 */
public interface StudentDao {
    List<Student> query();

    void save(Student student);
}
