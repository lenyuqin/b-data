package com.site.datasourse.getdata;

import java.sql.Date;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.site.common.entity.*;
import com.site.common.service.*;
import com.site.datasourse.constants.Constants;
import com.site.datasourse.constants.bilibiliConstants;
import com.site.component.utils.text.BVStringUtil;
import com.site.datasourse.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;


/**
 * @author lenyuqin
 * 这里分几个方法，一个爬取视频排行榜的数据，一个爬取热门数据，然后将数据一块插入
 */
@Slf4j
@Service // 2.开启多线程
public class BilibiliRank {


    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoDataService bVideoDataService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BAuthorBasedataService bAuthorBasedataService;
    @Resource
    private BPopularDataService bPopularDataService;
    @Resource
    private BOnlineService bOnlineService;
    @Resource
    private BCarouselService bCarouselService;

    /**
     * 爬取视频分区的排行榜,并保存数据库,这里由于视频数据很多，并且也要保存到视频数据中，一个是rank表，一个是history表，我这里热门需要
     * 分出来一个表格么，分都分出来了，不管了，这里爬取就一块都爬取得了，用个set的集合保存就行了,还有作者信息也要存进去，用一个全局的set保存作者和视频
     */
    public void getVideoRanklist() {
        HashSet<BAuthorBasedata> bAuthorBaseDataHashSet = new HashSet<>();
        HashSet<BVideoHistory> historyHashSet = new HashSet<>();
        List<BVideoRank> videoRankList = new ArrayList<>();
        List<BVideoData> videoDataList = new ArrayList<>();
        //我是真没想到，这里只需要一秒，而数据库下面居然要这么久！

        for (Constants constants : Constants.values()) {
            String body = HttpRequest.get(bilibiliConstants.VIDEO_LEADERBOARD_REQUEST_PREFIX + constants.getValue()).timeout(5000).execute().body();
            JSONArray jsonArray = JSON.parseObject(body).getJSONObject("data").getJSONArray("list");
            int videoRankCount = bilibiliConstants.VIDEO_DATA_FLAG;
            for (Object o : jsonArray) {
                JSONObject video = JSON.parseObject(o.toString());
                String pic = video.getString("pic");
                String title = video.getString("title");
                String desc = video.getString("desc");
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
                bVideoData.setBvReply(Integer.valueOf(reply));
                bVideoData.setBvDesc(desc);
                bVideoData.setBvPicUrl(pic);
                bVideoData.setBvTime(DateUtils.getLocalCurrentDate());

                BAuthorBasedata bAuthorBasedata = new BAuthorBasedata();
                bAuthorBasedata.setBvUpuuid(Long.valueOf(mid));
                bAuthorBasedata.setBvName(name);
                bAuthorBasedata.setBvFaceUrl(face);

                BVideoHistory bVideoHistory = new BVideoHistory();
                bVideoHistory.setBvNumber(bvid);
                bVideoHistory.setBvTitle(title);
                bVideoHistory.setBvUp(name);
                bVideoHistory.setBvUpuuid(mid);

                historyHashSet.add(bVideoHistory);
                bAuthorBaseDataHashSet.add(bAuthorBasedata);
                videoDataList.add(bVideoData);
                videoRankList.add(bVideoRank);
                videoRankCount++;
            }
        }


        //现在所有数据都有了
        bVideoHistoryService.saveOrUpdateBatch(historyHashSet);
        bAuthorBasedataService.saveOrUpdateBatch(bAuthorBaseDataHashSet);
        log.info("videoRankList===========>" + videoRankList.size() + "   videoDataList=============>" + videoDataList.size());
        //90多秒，就离谱！！！，这么会要这么久啊
        //long currentTimeMillis = System.currentTimeMillis();
        bVideoRankService.saveBatch(videoRankList, 1500);
        bVideoDataService.saveBatch(videoDataList, 1500);
        //log.info("一共花了====>" + (System.currentTimeMillis() - currentTimeMillis));
    }


    /**
     * 这个是爬取热门数据，同样的，热门数据也是要存入历史数据中
     * 然后调用一个定时任务，进行测试一下
     */
    public void getPopular() {
        HashSet<BVideoHistory> historyHashSet = new HashSet<>();
        HashSet<BAuthorBasedata> basedataHashSet = new HashSet<>();
        List<BPopularData> videoRankList = new ArrayList<>();
        int videoRankCount = bilibiliConstants.VIDEO_DATA_FLAG;
        //long currentTimeMillis = System.currentTimeMillis();
        for (int i = 1; i < 3; i++) {
            String body = HttpRequest.get(bilibiliConstants.HOT_VIDEO_REQUEST + i).timeout(500).execute().body();
            JSONArray jsonArray = JSON.parseObject(body).getJSONObject("data").getJSONArray("list");
            for (Object o : jsonArray) {
                JSONObject video = JSON.parseObject(o.toString());
                String pic = video.getString("pic");
                String title = video.getString("title");
                String desc = video.getString("desc");
                String bvid = video.getString("bvid");
                JSONObject owner = video.getJSONObject("owner");
                String face = owner.getString("face");//图片地址
                String mid = owner.getString("mid");//作者uuid
                String name = owner.getString("name");//作者昵称
                JSONObject stat = video.getJSONObject("stat");
                Long view = Long.valueOf(stat.getString("view"));
                Long danmaku = Long.valueOf(stat.getString("danmaku"));
                Long reply = Long.valueOf(stat.getString("reply"));
                Long favorite = Long.valueOf(stat.getString("favorite"));
                Long coin = Long.valueOf(stat.getString("coin"));
                Long share = Long.valueOf(stat.getString("share"));
                Long like = Long.valueOf(stat.getString("like"));
                BPopularData bPopularData = new BPopularData();
                bPopularData.setBvNumber(bvid);
                bPopularData.setBvTitle(title);
                bPopularData.setBvPicUrl(pic);
                bPopularData.setBvUp(name);
                bPopularData.setBvView(view);
                bPopularData.setBvLike(like);
                bPopularData.setBvCoin(coin);
                bPopularData.setBvDanmaku(danmaku);
                bPopularData.setBvShare(share);
                bPopularData.setBvUpuuid(mid);
                bPopularData.setBvDesc(desc);
                bPopularData.setBvUpFace(face);
                bPopularData.setBvRank(videoRankCount);
                bPopularData.setBvTime(DateUtils.getLocalCurrentDate());
                bPopularData.setBvFavorite(favorite);
                bPopularData.setBvReply(reply);
                videoRankList.add(bPopularData);

                BAuthorBasedata bAuthorBasedata = new BAuthorBasedata();
                bAuthorBasedata.setBvUpuuid(Long.valueOf(mid));
                bAuthorBasedata.setBvName(name);
                bAuthorBasedata.setBvFaceUrl(face);
                basedataHashSet.add(bAuthorBasedata);

                BVideoHistory bVideoHistory = new BVideoHistory();
                bVideoHistory.setBvNumber(bvid);
                bVideoHistory.setBvTitle(title);
                bVideoHistory.setBvUp(name);
                bVideoHistory.setBvUpuuid(mid);

                historyHashSet.add(bVideoHistory);
                videoRankCount++;
            }
        }
        //log.info("一共花了====>" + (System.currentTimeMillis() - currentTimeMillis));
        if (bPopularDataService.saveBatch(videoRankList) &&
                bVideoHistoryService.saveOrUpdateBatch(historyHashSet) &&
                bAuthorBasedataService.saveOrUpdateBatch(basedataHashSet)
        ) {
            log.info("热门榜爬取成功");
        } else {
            log.error("热门榜保存失败");
        }
    }


    //定期将redis存入mysql中

    /**
     * 获取每小时的在线视频的数据 online-list
     */
    public void getOnlineVideoData() throws Exception {
        List<BOnline> bOnlineArrayList = new ArrayList<>();
        Document parse = Jsoup.parse(new URL(bilibiliConstants.ONLINE_URL), 5000);
        Elements onlineList = parse.getElementsByClass("ebox");
        Elements script = parse.getElementsByTag("script");
        int flag = 0;
        for (Element element : onlineList) {
            String href = element.getElementsByTag("a").attr("href");
            String title = element.getElementsByTag("a").attr("title");
            String online = element.getElementsByTag("b").text();
            String author = element.getElementsByClass("author").text();
            String authorId = element.getElementsByClass("author").attr("href");
            log.info(++flag + " href=>" + href.split(bilibiliConstants.ONLINE_URL_PREFIX)[1] + "   title==>" + title + "   online==>" + online + "   author==>" + author + "   authorId==>" + authorId);
            BOnline bOnline = new BOnline();
            bOnline.setBvRank(flag);
            bOnline.setBvDate(DateUtils.getLocalCurrentDate());
            bOnline.setBvNumber(href.split(bilibiliConstants.ONLINE_URL_PREFIX)[1]);
            bOnline.setBvTitle(title);
            bOnline.setBvOnline(Integer.valueOf(online));
            bOnline.setBvAuthor(author);
            bOnline.setBvUpuuid(BVStringUtil.filterNumber(authorId));
            bOnline.setBvImg("");
            bOnlineArrayList.add(bOnline);
        }
        if (bOnlineService.saveBatch(bOnlineArrayList)) {
            log.info("在线视频数据保存成功");
        } else {
            log.error("在线视频数据保存失败");
        }


    }


    public void getCarousel() throws Exception {
        Document parse = Jsoup.parse(new URL(bilibiliConstants.MAIN_WEBSITE), 5000);
        Elements elementsByClass = parse.getElementsByClass("van-slide").select(".item");
        List<BCarousel> bCarouselArrayList = new ArrayList<>();
        for (Element element : elementsByClass) {
            String src = element.getElementsByTag("img").attr("src");
            String alt = element.getElementsByTag("img").attr("alt");
            BCarousel bCarousel = new BCarousel();
            bCarousel.setId(0);
            bCarousel.setDate(DateUtils.getLocalCurrentDate());
            bCarousel.setUrl(src);
            bCarousel.setTitle(alt);
            bCarouselArrayList.add(bCarousel);
        }
        if (bCarouselService.saveBatch(bCarouselArrayList)) {
            log.info("轮播图数据保存成功");
        } else {
            log.error("轮播图数据保存失败");
        }
    }

}
