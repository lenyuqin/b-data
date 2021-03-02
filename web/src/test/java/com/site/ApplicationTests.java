package com.site;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BHomePageData;
import com.site.common.entity.BVideoData;
import com.site.common.service.BHomePageDataService;
import com.site.common.service.BVideoDataService;
import com.site.datasourse.getdata.BilibiliRank;
import com.site.datasourse.getdata.BilibiliUp;
import com.site.datasourse.getdata.BilibiliVideo;
import com.site.datasourse.getdata.QuartzTask;
import com.site.datasourse.utils.DateUtils;
import com.site.web.entity.Card;
import com.site.web.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

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
    private QuartzTask quartzTask;

    @Resource
    private BHomePageDataService bHomePageDataService;

    @Resource
    private BilibiliUp bilibiliUp;
    @Resource
    private BilibiliVideo bilibiliVideo;
    @Resource
    private BVideoDataService bVideoDataService;

    @Resource
    private CardService cardService;

    @Test
    void test1() {
        //List<BHomePageData> list = bHomePageDataService.list();
        //list.forEach(System.out::println);
        //long currentTimeMillis = System.currentTimeMillis();
        //bilibiliUp.getAuthorLikeList();
        //log.info("一共花了====>" + (System.currentTimeMillis() - currentTimeMillis));
    }

    //BV1gg4y1q7uW bug视频

    @Test
    void test2() throws Exception {
        long l = System.currentTimeMillis();
        List<BVideoData> list = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().groupBy(BVideoData::getBvNumber).having("COUNT(BV_NUMBER) < 7 "));
        int flag = 0;
        for (BVideoData bVideoData : list) {
            bilibiliVideo.getVideoData(bVideoData);
            log.info("flag=>" + flag++);
        }
        log.info("所用时间=====>" + (System.currentTimeMillis() - l));
    }

    @Test
    void test3() throws Exception {
        //bilibiliRank.getOnlineVideoData();
        bilibiliRank.getCarousel();
    }
}
