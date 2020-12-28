package com.site;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BHomePageData;
import com.site.common.service.BHomePageDataService;
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

    @Resource
    private BHomePageDataService bHomePageDataService;

    @Test
    void test1() {
        //long currentTimeMillis = System.currentTimeMillis();
        //bilibiliRank.getVideoRanklist();
        //bilibiliRank.getPopular();
        //log.info("一共花了====>" + (System.currentTimeMillis() - currentTimeMillis));
        //BHomePageData one = bHomePageDataService.getOne(new QueryWrapper<BHomePageData>().lambda().select(BHomePageData::getBvTotalVisits).orderByDesc(BHomePageData::getBvDay).last("limit 1"));

        //log.info(one.toString());
    }
}
