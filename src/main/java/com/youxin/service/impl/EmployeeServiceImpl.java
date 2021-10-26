package com.youxin.service.impl;

import com.youxin.entities.Employee;
import com.youxin.mapper.EmployeeMapper;
import com.youxin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youxin
 * @program guojihua
 * @description EmployeeServiceImpl
 * @date 2021-10-22 17:40
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeMapper.findAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public void delEmployee(Integer id) {
        employeeMapper.delEmployee(id);
    }

    @Override
    public void insEmployee(Employee employee) {
        employeeMapper.insEmployee(employee);
    }

    @Override
    public void updEmployee(Employee employee) {
        employeeMapper.updEmployee(employee);
    }
}
