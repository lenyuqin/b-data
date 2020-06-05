package com.site.bdata.datasources;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.entity.BVideoRank;

import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Programmer Li
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
public class DataUpload {

    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoDataService bVideoDataService;

    private static DataUpload dataUpload;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        dataUpload = this;
        dataUpload.bVideoRankService = this.bVideoRankService;
        dataUpload.bVideoHistoryService = this.bVideoHistoryService;
        dataUpload.bVideoDataService = this.bVideoDataService;
        // 初使化时将已静态化的testService实例化
    }


    /**
     * 上传全站日版数据
     */
    @Async
//    @Scheduled(cron = "0 0 12 ?")
    public void dailyRankDataUpLoadToSQL() {
        List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(0);
//        for (BVideoRank bVideoRank : bVideoRankList) {
//            System.out.println(bVideoRank);
//        }
        List<BVideoData> bVideoDataList = new ArrayList<>();
        List<BVideoHistory> bVideoHistoryList = bVideoRankList.stream().map(bVideoRank -> {
            BVideoHistory bVideoHistory = new BVideoHistory();
            bVideoHistory.setBvNumber(bVideoRank.getBvNumber());
            bVideoHistory.setBvTitle(bVideoRank.getBvTitle());
            bVideoHistory.setBvUp(bVideoRank.getBvUp());
            bVideoHistory.setBvUpuuid(bVideoRank.getBvUpuuid());
            return bVideoHistory;
        }).collect(Collectors.toList());
        boolean bVideoRankListflag = dataUpload.bVideoRankService.saveBatch(bVideoRankList);
        boolean bVideoHistoryListflag = dataUpload.bVideoHistoryService.saveOrUpdateBatch(bVideoHistoryList);
        if (bVideoRankListflag && bVideoHistoryListflag) {
            log.info("===========日版数据保存成功=============");
        } else {
            log.error("===========日版数据保存失败=============");
        }
        //查询历史表的所有数据,并将其视频的数据记录下来
        List<BVideoHistory> bVideoHistories = dataUpload.bVideoHistoryService.list();
        for (BVideoHistory bVideoHistory : bVideoHistories) {
            int bVideoDataCount = dataUpload.bVideoDataService.count(new QueryWrapper<BVideoData>().lambda().eq(BVideoData::getBvNumber, bVideoHistory.getBvNumber()));
            if (bVideoDataCount < bilibiliConstants.VIDEO_DATA_NUMBER) {
                //如果大于七条的数据就不加入数据库
                bVideoDataList.add(bilibiliVideo.BVdata(bVideoHistory));
                log.info("==============视频=>"+bVideoHistory.getBvNumber()+"录入中=================");
            }
        }
        boolean bVideoDataListflag = dataUpload.bVideoDataService.saveBatch(bVideoDataList);
        if (bVideoDataListflag) {
            log.info("===========视频数据保存成功=============");
        } else {
            log.error("===========视频数据保存失败=============");
        }
    }


}



