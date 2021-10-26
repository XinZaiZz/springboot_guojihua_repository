package com.youxin.mapper;


import com.youxin.entities.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentMapper {

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
