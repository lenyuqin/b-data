package com.site.bdata;

import com.site.bdata.datasources.bilibiliRank;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BdataApplicationTests {

    @Resource
    private BVideoRankService bVideoRankService;
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
    void testLoad(){
        
        List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(0);
        for (BVideoRank bVideoRank : bVideoRankList) {
            System.out.println(bVideoRank);
        }
//        bVideoRankService.saveBatch(bVideoRankList);
    }



}
