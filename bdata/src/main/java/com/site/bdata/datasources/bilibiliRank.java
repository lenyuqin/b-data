package com.site.bdata.datasources;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.bdata.constants.Constants;
import com.site.bdata.constants.LeaderboardTypeConstants;
import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.BVStringUtil;
import com.site.bdata.util.DateUtils;
import com.site.bdata.util.jsoupUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class bilibiliRank {

    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoDataService bVideoDataService;

    /**
     * @deprecated 这是老方式爬取了，效率太慢了
     * https://www.bilibili.com/video/BV1uf4y127Ab
     * 小时候看笑话，长大后看真实
     * //space.bilibili.com/13899470
     * 喜娃桑
     */
    public static List<BVideoRank> bVideoRankArraylist(Integer BvRankzone) {

        List<BVideoRank> bVideoRankList = new ArrayList<>();
        log.info("==========耐心等待几分钟==========");
        //        解析页面(jsoup返回document就是document对象)
        //Document document = jsoupUtil.getHtmlContent(bilibiliConstants.RANK_URL_PREFIX + LeaderboardTypeConstants.ALL.getValue() + "/" + BvRankzone + bilibiliConstants.RANK_URL_SUFFIX);
        //int videoRankCount = bilibiliConstants.VIDEO_DATA_FLAG;
        //for (Element element : document.select("li[class=rank-item]")) {
        //    String bvNumber = element.select(".img").select("a").attr("href").split("/")[4];
        //    String bvTitle = BVStringUtil.filterEmoji(element.select(".img").select("img").attr("alt"));
        //    String bvUpuuid = element.select(".detail").select("a").attr("href").split("/")[3];
        //    String bvUp = BVStringUtil.filterEmoji(element.select(".detail").select("a").select("span").text());
        //    String bvScore = element.select(".pts").text().split(" ")[0];
        //    BVideoRank bVideoRank = new BVideoRank();
        //    bVideoRank.setBvNumber(bvNumber);
        //    bVideoRank.setBvRanknum(videoRankCount);
        //    bVideoRank.setBvTitle(bvTitle);
        //    bVideoRank.setBvTime(DateUtils.getLocalCurrentDate());
        //    bVideoRank.setBvRankzone(BvRankzone);
        //    bVideoRank.setBvScore(bvScore);
        //    bVideoRank.setBvUp(bvUp);
        //    bVideoRank.setBvUpuuid(bvUpuuid);
        //    bVideoRankList.add(bVideoRank);
        //    videoRankCount++;
        //}

        return bVideoRankList;

    }


    /**
     * 爬取视频分区的排行榜,并保存数据库
     *
     * @return videoRankList
     */
    public void getVideoRanklist() {
        for (Constants constants : Constants.values()) {
            List<BVideoRank> videoRankList = new ArrayList<>();
            List<BVideoData> videoDataList = new ArrayList<>();
            String body = HttpRequest.get(bilibiliConstants.VIDEO_LEADERBOARD_REQUEST_PREFIX + constants.getValue()).timeout(500).execute().body();
            JSONArray jsonArray = JSON.parseObject(body).getJSONObject("data").getJSONArray("list");
            int videoRankCount = bilibiliConstants.VIDEO_DATA_FLAG;
            for (Object o : jsonArray) {
                JSONObject video = JSON.parseObject(o.toString());
                String aid = video.getString("aid");
                String pic = video.getString("pic");
                String title = video.getString("title");
                String bvid = video.getString("bvid");
                String score = video.getString("score");
                JSONObject owner = video.getJSONObject("owner");
                String face = owner.getString("face");//图片地址
                String mid = owner.getString("mid");//作者uuid
                String name = owner.getString("name");//作者昵称
                JSONObject stat = video.getJSONObject("stat");
                String view = stat.getString("view");
                String danmaku = stat.getString("danmaku");
                String reply = stat.getString("reply");
                String favorite = stat.getString("favorite");
                String coin = stat.getString("coin");
                String share = stat.getString("share");
                String like = stat.getString("like");
                BVideoRank bVideoRank = new BVideoRank();
                bVideoRank.setBvNumber(bvid);
                bVideoRank.setBvUp(BVStringUtil.filterEmoji(name));
                bVideoRank.setBvTitle(BVStringUtil.filterEmoji(title));
                bVideoRank.setBvScore(score);
                bVideoRank.setBvRankzone(constants.getValue());
                bVideoRank.setBvRanknum(videoRankCount);
                bVideoRank.setBvUpuuid(mid);
                bVideoRank.setBvTime(DateUtils.getLocalCurrentDate());
                BVideoData bVideoData = new BVideoData();
                bVideoData.setBvNumber(bvid);
                bVideoData.setBvUp(BVStringUtil.filterEmoji(name));
                bVideoData.setBvTitle(BVStringUtil.filterEmoji(title));
                bVideoData.setBvLikenum(Long.valueOf(like));
                bVideoData.setBvCoinnum(Long.valueOf(coin));
                bVideoData.setBvCollectnum(Long.valueOf(favorite));
                bVideoData.setBvSharenum(Long.valueOf(share));
                bVideoData.setBvViewnum(Long.valueOf(view));
                bVideoData.setBvDmnum(Long.valueOf(danmaku));
                bVideoData.setBvUpuuid(mid);
                bVideoData.setBvTime(DateUtils.getLocalCurrentDate());
                videoRankCount++;
                videoDataList.add(bVideoData);
                videoRankList.add(bVideoRank);
            }
            //现在所有数据都有了
            boolean batch = bVideoRankService.saveBatch(videoRankList);
            boolean batch1 = bVideoDataService.saveBatch(videoDataList);
            log.info("这是==>" + constants.getDescription() + "===========排行榜===保存成功");

        }


    }


}
