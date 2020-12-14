package com.site.bdata;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.datasources.bilibiliRank;
import com.site.bdata.dto.form.AjaxResultPage;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Resource
    private bilibiliRank bilibiliRank;


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
        List<BVideoData> list = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().between(BVideoData::getBvTime, timestamp, timestamp1));
        AjaxResultPage<BVideoData> ajaxResultPage = new AjaxResultPage<>();
        ajaxResultPage.setMsg("hello");
        ajaxResultPage.setCode(0);
        ajaxResultPage.setData(list);

        JSONObject jsonObject = JSONUtil.parseObj(ajaxResultPage);
        jsonObject.get("data");
        System.out.println();


//        System.out.println(jsonObject);
//        Object o = jsonObject.getJSONObject("data").get("bvNumber");
//        System.out.println(o);

    }

    @Test
    void testData() {
        List<BVideoData> bVideoDataList = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().eq(BVideoData::getBvNumber, "BV13T4y1J7bS"));

        for (BVideoData bVideoData : bVideoDataList) {
            bVideoData.setBvTime(DateUtils.formatDate(bVideoData.getBvTime()));
            log.info(bVideoData.toString());
        }
        Timestamp dateBegin = Timestamp.valueOf(bilibiliConstants.DATE_BEGIN);
        Timestamp dateEnd = Timestamp.valueOf(bilibiliConstants.DATE_END);
        BVideoData bVideoDataOne = bVideoDataService.getOne(new QueryWrapper<BVideoData>()
                .lambda().between(BVideoData::getBvTime, dateBegin, dateEnd).eq(BVideoData::getBvNumber, "BV1zz4y1R7MS"));
        System.out.println(bVideoDataOne);
    }

    /**
     * 测试修改集合元素的值
     */
    @Test
    void testList() {
        Page<BVideoRank> pagenum = new Page<>(1, 10);
        DateTime date = DateUtil.date();
        String format = "yyyy-MM-dd";


        List<BVideoRank> records = bVideoRankService.page(pagenum, new QueryWrapper<BVideoRank>()
                .lambda().eq(BVideoRank::getBvRankzone, 0)).getRecords();
        records.forEach(item -> {
            item.setBvTime(DateUtil.parse(DateUtil.format(item.getBvTime(), format)));
        });

        for (BVideoRank record : records) {
            System.out.println(record);
        }

    }


    /**
     * 测试新的接口的请求地址
     */
    @Test
    void testList2() {
        bilibiliRank.getVideoRanklist();
    }


}
