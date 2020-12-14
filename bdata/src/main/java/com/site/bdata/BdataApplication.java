package com.site.bdata;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.site")
@EnableCaching
public class BdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdataApplication.class, args);
    }



}
