package com.youxin.controller;

import com.youxin.entities.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

/*    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        if(username.equals("admin") && password.equals("123456")){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            session.setAttribute("msg","请输入正确的用户名密码！");
            return "redirect:/index.html";
        }
    }*/

    @RequestMapping("/toLogin")
    public String toLogin(HttpSession session) {
        session.setAttribute("msg","请先登陆！");
        return "index";
    }

    /**
     * @author youxin
     * @date 2021-10-19 17:07
     * @param username
     * @param password
     * @param model
     * @return java.lang.String
     * @throws
     * @since
     */
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, String password,String rememberMe, Model model,HttpSession session) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登陆数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (rememberMe != null && rememberMe.equals("remember-me")) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            return "dashboard";
        }catch (UnknownAccountException uae) {
            model.addAttribute("msg","用户名错误！");
            return "index";
        }catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg","密码错误！");
            return "index";
        }
    }

    @RequestMapping("/unAuthorized")
    public String unAuthorized(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("unAuthorized","授权未成功!");
        return "dashboard";
    }

    @RequestMapping("/user/loginout")
    public String logout() {
        Subject currentSubject = SecurityUtils.getSubject();
        currentSubject.logout();
        return "redirect:/toLogin";
    }

    @RequestMapping("/main")
    public String mainPage(){
        return "dashboard";
    }
}
