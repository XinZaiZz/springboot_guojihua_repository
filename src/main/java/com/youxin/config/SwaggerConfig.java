package com.youxin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * @author youxin
 * @program springboot-guojihua
 * @description Swagger配置类
 * @date 2021-10-18 17:16
 */
@Configuration
@EnableSwagger2   //开启swagger2
public class SwaggerConfig {

    //注入docket实体bean
    @Bean
    public Docket docket(Environment environment) {

        //获取环境
        Profiles profiles = Profiles.of("dev","test");
        //获取环境后判断是否是dev 和 test环境
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("youxin")
                //判断环境，确认是否开启swagger
                .enable(flag)
                .select()
                //过滤什么路径
                .paths(PathSelectors.ant("/**"))
                /*
                *RequestHandlerSelectors  配置要扫描接口的方式
                * basePackage()  指定要扫描的包
                * any()  扫描全部
                * none()  不扫描
                * withClassAnnotation()  扫描类上的注解，参数为一个反射类对象
                * withMethodAnnotation()  扫描方法上的注解类
                * */
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.basePackage("com.youxin.controller"))
                .build();
    }

    //配置Swagger信息apiInfo
    public ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("youxin", "", "1511980683@qq.com");

        return new ApiInfo(
                "youxin的文档",
                "Api Documentation",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
