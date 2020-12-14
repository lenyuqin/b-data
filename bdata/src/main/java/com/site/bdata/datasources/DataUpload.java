package com.site.bdata.datasources;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.bdata.constants.Constants;
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
 *
 * 将不用这种方式爬取数据，直接在类文件当中实现数据爬取和数据存储的问题
 */
@Slf4j
@Component
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
    public void dailyRankDataUpLoadToSQL() {
        for (Constants e : Constants.values()) {
            List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(e.getValue());
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
                log.info("===========" + e.getValue() + "榜数据保存成功=============");
            } else {
                log.error("===========" + e.getValue() + "榜数据保存失败=============");
            }
        }
    }

    /**
     * 录入视频数据
     */
    public void dailyVideoDataUpLoadToSQL() {
        //查询历史表的所有数据,并将其视频的数据记录下来
        log.info("=================历史视频的总数有=>" + dataUpload.bVideoHistoryService.count() + "=================");
        List<BVideoHistory> bVideoHistories = dataUpload.bVideoHistoryService.list();
        int bVideoDataflag = bilibiliConstants.VIDEO_DATA_FLAG;
        for (BVideoHistory bVideoHistory : bVideoHistories) {
            int bVideoDataCount = dataUpload.bVideoDataService.count(new QueryWrapper<BVideoData>()
                    .lambda().eq(BVideoData::getBvNumber, bVideoHistory.getBvNumber()));
            if (bVideoDataCount < bilibiliConstants.VIDEO_DATA_NUMBER) {
                //如果大于七条的数据就不加入数据库
                dataUpload.bVideoDataService.save(bilibiliVideo.BVdata(bVideoHistory));
                log.info("==============第" + bVideoDataflag + "个视频=>" + bVideoHistory.getBvNumber() + "录入成功=================");
                bVideoDataflag++;
            }
        }
        log.info("================全部数据保存成功=================");
    }

    /**
     * 录入排行榜的视频数据，不需要再去爬取每一个视频的数据了
     * 因为这里是用接口的数据区录入的，所以效率极高，但是！！！视频作者的数据还是要一个一个的爬取，不然获取不了
     */
    public void dailyVideoDataUpLoad() {
        bilibiliRank bilibiliRank = new bilibiliRank();
        bilibiliRank.getVideoRanklist();
        log.info("所有排行榜数据爬取成功");
    }


    /**
     * 录入作者的信息数据
     */
    public void upLoadAuthorData() {

    }


}



