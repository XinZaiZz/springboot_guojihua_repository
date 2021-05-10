package com.youxin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        if(username.equals("admin") && password.equals("123456")){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            session.setAttribute("msg","请输入正确的用户名密码！");
            return "redirect:/index.html";
        }
    }

    @RequestMapping("/main")
    public String mainPage(){
        return "dashboard";
    }
}
