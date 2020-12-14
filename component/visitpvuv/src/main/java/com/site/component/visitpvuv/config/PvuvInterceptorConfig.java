package com.site.component.visitpvuv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author lenyuqin
 * @data 2020/12/8
 * @describe 通过拦截login路径，来获取访问量
 */
@Configuration
public class PvuvInterceptorConfig implements WebMvcConfigurer {

    @Resource
    private PvuvInterceptor pvuvInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加的拦截地址
        registry.addInterceptor(pvuvInterceptor).addPathPatterns("/**");
    }

}
