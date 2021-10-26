package com.youxin.mapper;

import com.youxin.entities.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author youxin
 * @program guojihua
 * @description 员工mapper接口类
 * @date 2021-10-22 17:08
 */
@Repository
@Mapper
public interface EmployeeMapper {

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
