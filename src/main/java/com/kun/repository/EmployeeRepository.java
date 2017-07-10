package com.kun.repository;

import com.kun.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kun on 2017/4/21.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Integer>,
        JpaSpecificationExecutor<Employee>
{
    //where name =?
    Employee findByName(String name);

    //where name %? and age <?
    List<Employee> findByNameStartingWithAndAgeLessThan(String name, Integer age);

    //where name ?% and age<?
    List<Employee> findByNameEndingWithAndAgeLessThan(String name, Integer age);

    //where name in(...) or age<?
    List<Employee> findByNameInOrAgeLessThan(List<String> names, Integer age);

//    @Query("update Employee o set o.age=:age where o.id =:id")
    @Modifying
   @Query(nativeQuery = true,value = "update employee set age=:age where id=:id ")
    void updateAgeById(@Param("id") Integer id, @Param("age") Integer age);
}
