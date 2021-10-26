package com.youxin.filter;

import com.youxin.entities.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author youxin
 * @program guojihua
 * @description SessionFilter
 * @date 2021-10-25 18:58
 */
public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        //判断用户是通过记住我功能自动登录,此时session失效
        if (subject.isRemembered()) {
            try {
                User user = (User)(subject.getPrincipals().getPrimaryPrincipal());
                HttpSession session = ((HttpServletRequest)request).getSession();
                if(session.getAttribute("user")==null){
                    session.setAttribute("user", user);
                    //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
                    /*session.setTimeout(-1000l);*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request,response);
    }
}
