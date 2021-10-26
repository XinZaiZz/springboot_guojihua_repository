package com.youxin.service;

import com.youxin.entities.Department;
import com.youxin.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youxin
 * @program guojihua
 * @description DepartmentService
 * @date 2021-10-22 17:37
 */
@Service
public interface DepartmentService {

    //查询全部部门
    public List<Department> findAllDepartments();

    //根据部门id查询部门信息
    public Department getDepartmentById(Integer id);

    //通过id删除部门
    public void delDepartment(Integer id);

    //增加部门
    public void insDepartment(Department department);

    //修改部门
    public void updDepartment(Department department);
}
