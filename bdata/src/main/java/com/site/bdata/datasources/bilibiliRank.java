package com.site.bdata.datasources;

import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.util.DateUtils;
import com.site.bdata.util.jsoupUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class bilibiliRank {
    /**
     * https://www.bilibili.com/video/BV1uf4y127Ab
     * 小时候看笑话，长大后看真实
     * //space.bilibili.com/13899470
     * 喜娃桑
     */

    public static List<BVideoRank> bVideoRankArraylist(Integer BvRankzone) {

        List<BVideoRank> bVideoRankList = new ArrayList<>();
        log.info("==========耐心等待几分钟==========");
        //        解析页面(jsoup返回document就是document对象)
        Document document = jsoupUtil.getHtmlContent(bilibiliConstants.RANK_URL);
        int i=1;
        for (Element element : document.select("li[class=rank-item]")) {
            String bvNumber = element.select(".img").select("a").attr("href").split("/")[4];
            String bvTitle = element.select(".img").select("img").attr("alt");
            String bvUpuuid = element.select(".detail").select("a").attr("href").split("/")[3];
            String bvUp = element.select(".detail").select("a").select("span").text();
            String bvScore = element.select(".pts").text().split(" ")[0];
            BVideoRank bVideoRank = new BVideoRank();
            bVideoRank.setBvNumber(bvNumber);
            bVideoRank.setBvRanknum(i);
            bVideoRank.setBvTitle(bvTitle);
            bVideoRank.setBvTime(DateUtils.getLocalCurrentDate());
            bVideoRank.setBvRankzone(BvRankzone);
            bVideoRank.setBvScore(bvScore);
            bVideoRank.setBvUp(bvUp);
            bVideoRank.setBvUpuuid(bvUpuuid);
            bVideoRankList.add(bVideoRank);
            i++;
        }
        return bVideoRankList;

    }


}
