package com.youxin.controller;

import com.youxin.entities.Department;
import com.youxin.entities.Employee;
import com.youxin.service.DepartmentService;
import com.youxin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/emps")
    public String showAllEmployees(Model model){

        List<Employee> employees = employeeService.findAllEmployees();
//        System.out.println(employees);
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/add")
    public String toAddPage(Model model){
        //查询所有员工部门
        List<Department> departments = departmentService.findAllDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping("/add")
    public String addEmp(Employee employee){
//        System.out.println(employee);
        employeeService.insEmployee(employee);
        return "redirect:/emps";
    }

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

    //与addEmp()一样所以可以省略，并在update.html的action中写/add请求，再在dao层判断是否带入id属性
    @PostMapping("/update")
    public String update(Employee employee){
        employeeService.updEmployee(employee);
        return "redirect:/emps";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.delEmployee(id);
        return "redirect:/emps";
    }

    @RequestMapping("/user/loginout")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
