package com.site;

import com.site.datasourse.getdata.BilibiliRank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author lenyuqin
 * @data 2020/12/12
 */
@Slf4j
@SpringBootTest
public class ApplicationTests {

    @Resource
    private BilibiliRank bilibiliRank;

    @Test
    void test1() {
        //long currentTimeMillis = System.currentTimeMillis();
        ////bilibiliRank.getVideoRanklist();
        //bilibiliRank.getPopular();
        //log.info("一共花了====>" + (System.currentTimeMillis() - currentTimeMillis));
    }
}
