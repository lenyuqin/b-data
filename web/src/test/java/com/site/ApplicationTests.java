package com.site;

import com.site.datasourse.getdata.BilibiliRank;
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
    private BilibiliRank bilibiliRank;

    @Test
    void test1(){
        //bilibiliRank.getVideoRanklist();
        //bilibiliRank.getPopular();
    }




}
