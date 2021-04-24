package com.site.datasourse.getdata;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BHomePageData;
import com.site.common.entity.BVideoData;
import com.site.common.service.BAuthorBasedataService;
import com.site.common.service.BHomePageDataService;
import com.site.common.service.BVideoDataService;
import com.site.common.service.BVideoHistoryService;
import com.site.component.utils.pvuv.PvuvString;
import com.site.component.utils.pvuv.PvuvUtils;
import com.site.component.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenyuqin
 * @data 2020/12/27
 */
@Slf4j
@Service
public class QuartzTask {
    @Resource
    private BilibiliRank bilibiliRank;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private BHomePageDataService bHomePageDataService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BAuthorBasedataService bAuthorBasedataService;
    @Resource
    private BVideoDataService bVideoDataService;
    @Resource
    private BilibiliVideo bilibiliVideo;


    /**
     * 定时爬取数据 六小时一次
     */
    @Async
    @Scheduled(cron = "0 0 0/6 * * ? ")
    public void crawlData() {
        log.info("这是定时任务=====>" + "爬取数据");
        long currentTimeMillis = System.currentTimeMillis();
        bilibiliRank.getVideoRanklist();
        bilibiliRank.getPopular();
        log.info("数据爬取一共花了====>" + (System.currentTimeMillis() - currentTimeMillis));
    }


    /**
     * 定时将redis中的数据存入数据库中 一天一次 0点开始 所以获取的是昨天的数据
     */
    @Async
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void redisToMysql() {
        BHomePageData bHomePageData = new BHomePageData();
        log.info("这是定时任务=====>" + "将redis中的数据存入数据库中");
        int DayVisits = 0;
        if (redisUtil.hHasKey(PvuvString.DAILY_COUNT, PvuvUtils.getYesterdayKey())) {
            DayVisits = (int) redisUtil.hget(PvuvString.DAILY_COUNT, PvuvUtils.getYesterdayKey());
        }
        BHomePageData bvTotalVisits = bHomePageDataService.getOne(new QueryWrapper<BHomePageData>().lambda().select(BHomePageData::getBvTotalVisits).orderByDesc(BHomePageData::getBvDay).last("limit 1"));
        bHomePageData.setBvTotalVisits(bvTotalVisits.getBvTotalVisits() + DayVisits);
        bHomePageData.setBvDay(PvuvUtils.getYesterday());
        bHomePageData.setBvDayVisits(DayVisits);
        bHomePageData.setBvTotalVideo(bVideoHistoryService.count());
        bHomePageData.setBvTotalUp(bAuthorBasedataService.count());
        bHomePageData.setBvHistoryVisits((int) redisUtil.pfcount(PvuvString.COUNT));
        bHomePageDataService.save(bHomePageData);
    }

    /**
     * 每天3点执行一次，用于爬取全部数据的
     * TODO 这里要改一下，到时候爬取接口，每四个小时爬取一次，不然真的效率太低了，还不如爬取接口来的快
     */
    @Async
    @Scheduled(cron = "0 0 3/23 * * ? ")
    public void getVideoData() throws Exception {
        long l = System.currentTimeMillis();
        List<BVideoData> list = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().groupBy(BVideoData::getBvNumber).having("COUNT(BV_NUMBER) < 7 "));
        int flag = 0;
        for (BVideoData bVideoData : list) {
            bilibiliVideo.getVideoData(bVideoData);
            log.info("flag=>" + flag++);
        }
        log.info("所用时间=====>" + (System.currentTimeMillis() - l));
    }


    /**
     * 获取每小时的在线视频的数据
     */
    @Async
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void getOnlineVideoData() throws Exception {
        bilibiliRank.getOnlineVideoData();
    }

    /**
     * 每天获取b站的轮播图
     */
    @Async
    @Scheduled(cron = "0 0 12 1/1 * ? ")
    public void getPicture() throws Exception {
        bilibiliRank.getCarousel();
    }

}
