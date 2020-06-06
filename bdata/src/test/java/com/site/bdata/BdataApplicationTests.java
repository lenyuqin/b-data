package com.site.bdata;

import cn.hutool.extra.emoji.EmojiUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.site.bdata.datasources.DataUpload;
import com.site.bdata.datasources.bilibiliRank;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.BVStringUtil;
import com.site.bdata.util.DateUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class BdataApplicationTests {

    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoDataService bVideoDataService;

    @Test
    void contextLoads() {
        BVideoRank bVideoRank = new BVideoRank();
        bVideoRank.setBvNumber("BV1mk4y1675r");
        bVideoRank.setBvRanknum(27);
        bVideoRank.setBvTitle("希望阿姨没事\uD83D\uDE4F");
        bVideoRank.setBvTime(DateUtils.getLocalCurrentDate());
        bVideoRank.setBvRankzone(1);
        bVideoRank.setBvScore("10782");
        bVideoRank.setBvUp("李小吴呦");
        bVideoRank.setBvUpuuid("3978558893");
        bVideoRankService.save(bVideoRank);
    }

    @Test
    void testLoad() {
//        String alias = EmojiUtil.toAlias("🙏");//:smile:
//        String emoji = BVStringUtil.filterEmoji("★⑥檤轮囬★");
////        String emoji = EmojiUtil.toUnicode("\uD83D\uDE4F");//😄
//        System.out.println(emoji);


        List<BVideoHistory> bVideoHistoryList = new ArrayList<>();

        List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(0);
        if (CollectionUtils.isNotEmpty(bVideoRankList)) {
            bVideoHistoryList = bVideoRankList.stream().map(bVideoRank -> {
                BVideoHistory bVideoHistory = new BVideoHistory();
                bVideoHistory.setBvNumber(bVideoRank.getBvNumber());
                bVideoHistory.setBvTitle(bVideoRank.getBvTitle());
                bVideoHistory.setBvUp(bVideoRank.getBvUp());
                bVideoHistory.setBvUpuuid(bVideoRank.getBvUpuuid());
                return bVideoHistory;
            }).collect(Collectors.toList());
        }
        boolean saveBatch = bVideoHistoryService.saveOrUpdateBatch(bVideoHistoryList);
        if (saveBatch) {
            System.out.println("成功!!!!!!!!!!");
        } else {
            System.out.println("失败!!!!!!!!!!");
        }


    }

    @Test
    void testVideo() {
//        int bVideoDataCount = bVideoDataService.count(new QueryWrapper<BVideoData>().lambda().eq(BVideoData::getBvNumber, "BV1hz411i7cD"));
//
//        System.out.println(bVideoDataCount);
//        DataUpload dataUpload = new DataUpload();
//        dataUpload.dailyRankDataUpLoadToSQL();
        Timestamp timestamp = Timestamp.valueOf("2020-06-05 00:00:0");
        Timestamp timestamp1 = Timestamp.valueOf("2020-06-05 23:59:59");
        List<BVideoData> list = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().between(BVideoData::getBvTime, timestamp,timestamp1));
        for (BVideoData bVideoData : list) {
            bVideoData.setBvTime(DateUtils.formatDate(bVideoData.getBvTime()));
            log.info(bVideoData.toString());
        }
    }

    @Test
    void testData(){
        List<BVideoData> bVideoDataList = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().eq(BVideoData::getBvNumber, "BV13T4y1J7bS"));

        for (BVideoData bVideoData : bVideoDataList) {
            bVideoData.setBvTime(DateUtils.formatDate(bVideoData.getBvTime()));
            log.info(bVideoData.toString());
        }
    }


}
