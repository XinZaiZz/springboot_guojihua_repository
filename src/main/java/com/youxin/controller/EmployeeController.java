package com.youxin.controller;

import com.youxin.entities.Department;
import com.youxin.entities.Employee;
import com.youxin.service.DepartmentService;
import com.youxin.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api("员工相关接口")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @ApiOperation("查询所有员工信息接口")
    @RequestMapping("/emps")
    public String showAllEmployees(Model model){

        List<Employee> employees = employeeService.findAllEmployees();
//        System.out.println(employees);
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @ApiOperation("跳转到添加员工界面")
    @GetMapping("/add")
    public String toAddPage(Model model){
        //查询所有员工部门
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @ApiOperation("添加员工")
    @PostMapping("/add")
    public String addEmp(Employee employee){
//        System.out.println(employee);
        employeeService.insEmployee(employee);
        return "redirect:/emps";
    }

    @ApiOperation("跳转到修改员工信息界面")
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model){
        //查询所选择员工信息
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("emp",employee);
        //查询所有员工部门
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("depts",departments);
        return "emp/update";
    }

    @ApiOperation("修改员工信息")
    //与addEmp()一样所以可以省略，并在update.html的action中写/add请求，再在dao层判断是否带入id属性
    @PostMapping("/update")
    public String update(Employee employee){
        employeeService.updEmployee(employee);
        return "redirect:/emps";
    }

    @ApiOperation("删除员工")
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.delEmployee(id);
        return "redirect:/emps";
    }

    /*@ApiOperation("登出")
    @RequestMapping("/user/loginout")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }*/
}
