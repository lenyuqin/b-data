package com.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author lenyuqin
 */
@EnableCaching
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@SpringBootApplication(scanBasePackages = {"com.site"},exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
