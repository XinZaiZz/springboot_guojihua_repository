package com.youxin.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("员工实体类")
public class Employee {
    @ApiModelProperty("员工id")
    private Integer id;

    @ApiModelProperty("员工姓名")
    private String lastName;

    @ApiModelProperty("员工邮箱地址")
    private String email;

    //1 male, 0 female
    @ApiModelProperty("员工性别：1 male, 0 female")
    private Integer gender;

    @ApiModelProperty("员工部门id")
    private Integer departmentId;

    @ApiModelProperty("员工出生日期")
    private Date birth;

    @ApiModelProperty("员工部门")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", departmentId=" + departmentId +
                ", birth=" + birth +
                ", department=" + department +
                '}';
    }

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, Integer gender, Integer departmentId, Date birth, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departmentId = departmentId;
        this.birth = birth;
        this.department = department;
    }
}
