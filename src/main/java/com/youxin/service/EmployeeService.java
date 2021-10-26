package com.youxin.service;

import com.youxin.entities.Employee;

import java.util.List;

/**
 * @author youxin
 * @program guojihua
 * @description EmployeeService
 * @date 2021-10-22 17:37
 */
public interface EmployeeService {
    //查询全部员工
    public List<Employee> findAllEmployees();

    //根据员工id查询员工信息
    public Employee getEmployeeById(Integer id);

    //通过id删除员工
    public void delEmployee(Integer id);

    //增加员工
    public void insEmployee(Employee employee);

    //修改员工
    public void updEmployee(Employee employee);
}
