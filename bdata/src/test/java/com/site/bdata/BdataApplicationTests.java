package com.site.bdata;

import cn.hutool.extra.emoji.EmojiUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.site.bdata.datasources.bilibiliRank;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.BVStringUtil;
import com.site.bdata.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class BdataApplicationTests {

    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
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
//        String alias = EmojiUtil.toAlias("🙏");//:smile:
//        String emoji = BVStringUtil.filterEmoji("★⑥檤轮囬★");
////        String emoji = EmojiUtil.toUnicode("\uD83D\uDE4F");//😄
//        System.out.println(emoji);


        List<BVideoHistory> bVideoHistoryList=new ArrayList<>();

        List<BVideoRank> bVideoRankList = bilibiliRank.bVideoRankArraylist(0);
        if (CollectionUtils.isNotEmpty(bVideoRankList)){
            bVideoHistoryList=bVideoRankList.stream().map(bVideoRank->{
                BVideoHistory bVideoHistory=new BVideoHistory();
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



}
