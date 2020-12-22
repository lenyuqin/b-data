package com.site;

import com.site.web.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author lenyuqin
 * @data 2020/12/12
 */
@SpringBootTest
public class ApplicationTests {

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    void test1(){
        System.out.println(sysUserMapper.selectByUsername("admin"));
    }

}
