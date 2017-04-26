package com.kun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kun.domain.Employee;
import com.kun.repository.EmployeeRepository;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 事物一般控制在service层，更新语句要加@Modifying
     *
     * @param id
     * @param age
     */
    @Transactional
    public void updateAgeById(Integer id,Integer age){
        employeeRepository.updateAgeById(id,age);
    }

    @Transactional
    public void save(List<Employee> employees){
        employeeRepository.save(employees);
    }
}
