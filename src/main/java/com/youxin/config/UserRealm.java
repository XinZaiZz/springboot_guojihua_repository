package com.youxin.config;

import com.youxin.entities.User;
import com.youxin.service.impl.LoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author youxin
 * @program springboot-shiro
 * @description UserRealm
 * @date 2021-10-15 16:48
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    LoginServiceImpl loginService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("执行了授权=>doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登陆的用户
        Subject subject = SecurityUtils.getSubject();
        //因为return new SimpleAuthenticationInfo(user,user.getPassword(),"");中返回了user所以能够获取user
        User currentUser = (User) subject.getPrincipal();
        //获取数据库中的权限
        String permStr = currentUser.getPerms();
        List<String> perms_list = Arrays.asList(permStr.split(","));
        //添加权限
        //添加一个权限
//        info.addStringPermission(currentUser.getPerms());
        //添加多个权限
        info.addStringPermissions(perms_list);
        return info;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("执行了认证=>doGetAuthorizationInfo");
//        //仿照用户名密码
//        String username = "root";
//        String password = "1234";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;


        //连接真实数据库
        User user = loginService.queryUserByName(userToken.getUsername());

        //用户名验证
        if (user == null) {
            return null;
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user.getName());

        //加密的盐值加密
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        //密码验证由shiro完成
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
//        return new SimpleAuthenticationInfo("",user.getPassword(),salt,"");
    }
}
