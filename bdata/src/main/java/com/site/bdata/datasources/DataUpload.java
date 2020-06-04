package com.site.bdata.datasources;

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
        dataUpload.bVideoDataService=this.bVideoDataService;
        // 初使化时将已静态化的testService实例化
    }


    /**
     * 上传全站日版数据
     */
    @Async
    public void dailyRankDataUpLoadToSQL() {
        List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(0);
        List<BVideoData> bVideoDataList = new ArrayList<>();
        List<BVideoHistory> bVideoHistoryList = bVideoRankList.stream().map(bVideoRank -> {
            BVideoHistory bVideoHistory = new BVideoHistory();
            bVideoHistory.setBvNumber(bVideoRank.getBvNumber());
            bVideoHistory.setBvTitle(bVideoRank.getBvTitle());
            bVideoHistory.setBvUp(bVideoRank.getBvUp());
            bVideoHistory.setBvUpuuid(bVideoRank.getBvUpuuid());
            return bVideoHistory;
        }).collect(Collectors.toList());
        for (BVideoHistory bVideoHistory : bVideoHistoryList) {
            BVideoData bVideoData = bilibiliVideo.BVdata(bVideoHistory);
            bVideoDataList.add(bVideoData);
        }
        boolean bVideoDataListflag = dataUpload.bVideoDataService.saveBatch(bVideoDataList);



        boolean bVideoRankListflag = dataUpload.bVideoRankService.saveBatch(bVideoRankList);
        boolean bVideoHistoryListflag = dataUpload.bVideoHistoryService.saveOrUpdateBatch(bVideoHistoryList);
        if (bVideoRankListflag && bVideoHistoryListflag&&bVideoDataListflag) {
            log.info("===========日版数据保存成功=============");
        } else {
            log.error("===========日版数据保存失败=============");
        }
    }


}



