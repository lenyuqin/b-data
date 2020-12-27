package com.site.datasourse.getdata;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.site.common.entity.BHomePageData;
import com.site.common.service.BAuthorBasedataService;
import com.site.common.service.BHomePageDataService;
import com.site.common.service.BVideoHistoryService;
import com.site.component.utils.pvuv.PvuvString;
import com.site.component.utils.pvuv.PvuvUtils;
import com.site.component.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

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
        long DayVisits = 0;
        if (redisUtil.hHasKey(PvuvString.DAILY_COUNT, PvuvUtils.getYesterdayKey())) {
            DayVisits = (long) redisUtil.hget(PvuvString.DAILY_COUNT, PvuvUtils.getYesterdayKey());
        }
        BHomePageData bvTotalVisits = bHomePageDataService.getOne(new QueryWrapper<BHomePageData>().lambda().select(BHomePageData::getBvTotalVisits).orderByDesc(BHomePageData::getBvDay).last("limit 1"));
        bHomePageData.setBvTotalVisits(bvTotalVisits.getBvTotalVisits()+DayVisits);
        bHomePageData.setBvDay((Date) PvuvUtils.getYesterday());
        bHomePageData.setBvDayVisits(DayVisits);
        bHomePageData.setBvTotalVideo((long) bVideoHistoryService.count());
        bHomePageData.setBvTotalUp((long) bAuthorBasedataService.count());
        bHomePageData.setBvHistoryVisits(redisUtil.pfcount(PvuvString.COUNT));
        bHomePageDataService.save(bHomePageData);
    }


}
