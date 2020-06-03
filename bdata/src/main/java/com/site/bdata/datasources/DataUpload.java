package com.site.bdata.datasources;

import com.site.bdata.entity.BVideoRank;
import com.site.bdata.mapper.BVideoDataMapper;
import com.site.bdata.mapper.BVideoHistoryMapper;
import com.site.bdata.mapper.BVideoRankMapper;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Programmer Li
 */
@Slf4j
@Component
@EnableScheduling
public class DataUpload {
    @Resource
    private BVideoDataMapper bVideoDataMapper;
    @Resource
    private BVideoHistoryMapper bVideoHistoryMapper;
    @Resource
    private BVideoRankMapper bVideoRankMapper;
    @Resource
    private BVideoDataService bVideoDataService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoRankService bVideoRankService;


    public void dataUploadToSQL(){
        List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(0);
        for (BVideoRank bVideoRank : bVideoRankList) {
            System.out.println(bVideoRank);
            bVideoRankService.save(bVideoRank);
        }

//        bVideoRankService.saveBatch(bVideoRankList);

    }



}
