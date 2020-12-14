package com.site.bdata.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * jwt权限配置拦截器,这里可以进行路径拦截
 */
@Configuration
//@ConditionalOnProperty(name = "project.jwt.pattern-path", havingValue = "true")//这个注解相当于读取配置文件
public class JwtInterceptorConfig implements WebMvcConfigurer {

    @Resource
    private AuthenticationInterceptor authenticationInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加的拦截地址
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/level1/**")
                .addPathPatterns("/level2/**")
                .addPathPatterns("/level3/**");

    }
}
