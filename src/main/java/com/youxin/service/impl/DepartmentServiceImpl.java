package com.youxin.service.impl;

import com.youxin.entities.Department;
import com.youxin.entities.Employee;
import com.youxin.mapper.DepartmentMapper;
import com.youxin.mapper.EmployeeMapper;
import com.youxin.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public List<Department> findAllDepartments() {
        return departmentMapper.findAllDepartments();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public void delDepartment(Integer id) {
        departmentMapper.delDepartment(id);
    }

    @Override
    public void insDepartment(Department department) {
        departmentMapper.insDepartment(department);
    }

    @Override
    public void updDepartment(Department department) {
        departmentMapper.updDepartment(department);
    }
}
