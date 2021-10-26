package com.youxin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.youxin.filter.SessionFilter;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author youxin
 * @program springboot-shiro
 * @description ShiroConfig
 * @date 2021-10-15 16:47
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean 第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
        * anon : 无需认证就可以访问
        * authc : 必须认证了才让访问
        * user : 必须拥有记住我功能才能使用
        * perms : 必须拥有对某个资源的权限才能访问
        * role : 必须拥有某个角色权限才能访问
        * */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/user/add","authc");
//        filterChainDefinitionMap.put("/user/update","authc");

        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/add","perms[user:add]");
        filterChainDefinitionMap.put("/update","perms[user:update]");
        filterChainDefinitionMap.put("/delete","perms[user:delete]");
        //其他资源都需要认证  authc 表示需要认证才能进行访问 user表示配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //设置拦截成功后的登陆请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置授权认证失败请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");

        /*//创建一个Map类型的对象存储自定义的sessionFilter(),添加到shiroFilterFactoryBean
        Map<String, Filter> filters = new HashMap<String,Filter>();
        filters.put("sessionFilter",sessionFilter());
        shiroFilterFactoryBean.setFilters(filters);*/
        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager 第二步
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(cookieRememberMeManager());
        //关联
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //创建Realm对象，第一步
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合shiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

/*    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        //cookie的存储时间，为 3600*24*14
        simpleCookie.setMaxAge(1209600);
        simpleCookie.setHttpOnly(true);
        simpleCookie.setName("rememberMe");
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }*/

    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
//        simpleCookie.setPath("/");
        //cookie的存储时间，为 3600*24*14
        simpleCookie.setMaxAge(1209600);
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }

    /**
     1. 此方法把该过滤器实例化成一个bean,否则在拦截器里无法注入其它bean
     2. 没有亲自操作过，看别人的代码这样说的
     3. @return
     */
    @Bean
    public SessionFilter sessionFilter(){return new SessionFilter();}
}
