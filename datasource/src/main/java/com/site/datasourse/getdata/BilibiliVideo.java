package com.site.datasourse.getdata;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;


import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BVideoData;
import com.site.common.entity.BVideoHistory;
import com.site.common.service.BVideoDataService;
import com.site.common.service.BVideoHistoryService;
import com.site.common.service.BVideoRankService;
import com.site.component.utils.text.ChineseToNum;
import com.site.datasourse.constants.bilibiliConstants;
import com.site.component.utils.text.BVStringUtil;
import com.site.datasourse.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class BilibiliVideo {
    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoDataService bVideoDataService;


    /**
     * 获取单个视频的视频数据 播放量 弹幕数量 点赞 收藏 硬币 分享
     *
     * @param bVideoData 视频bv号
     * @return Boolean
     * @throws Exception
     */
    public Boolean getVideoData(BVideoData bVideoData) throws Exception {
        log.info("bvNumber====>" + bVideoData.getBvNumber());
        BVideoData data = new BVideoData();
        data.setBvDesc(bVideoData.getBvDesc());
        data.setBvPicUrl(bVideoData.getBvPicUrl());
        data.setBvUp(bVideoData.getBvUp());
        data.setBvTitle(bVideoData.getBvTitle());
        data.setBvTime(DateUtils.getLocalCurrentDate());
        data.setBvNumber(bVideoData.getBvNumber());
        String url = bilibiliConstants.VIDEO_URL + bVideoData.getBvNumber();
        Document parse = Jsoup.parse(new URL(url), 50000);
        //System.out.println(parse.body());
        //判断视频是否存在 以及视频时候是动漫或者剧之类的  media-rating
        if (parse.getElementsByClass("error-text").isEmpty() && parse.getElementsByClass("media-rating").isEmpty()) {
            Long view = Long.valueOf(BVStringUtil.filterNumber(parse.getElementsByClass("view").attr("title")));
            Long dm = Long.valueOf(BVStringUtil.filterNumber(parse.getElementsByClass("dm").attr("title")));
            Long like = Long.valueOf(BVStringUtil.filterNumber(parse.getElementsByClass("like").attr("title")));
            Long coin = ChineseToNum.videoChineseToNum(BVStringUtil.filterNumber(parse.getElementsByClass("coin").text()));
            Long collect = ChineseToNum.videoChineseToNum(BVStringUtil.filterNumber(parse.getElementsByClass("collect").text()));
            Long share = ChineseToNum.videoChineseToNum(BVStringUtil.filterNumber(parse.getElementsByClass("share").text()));
            log.info("view=>" + view + " dm=>" + dm + " like=>" + like + " coin=>" + coin + " collect=>" + collect + " share=>" + share);
            data.setBvLikenum(like);
            data.setBvCoinnum(coin);
            data.setBvCollectnum(collect);
            data.setBvSharenum(share);
            data.setBvViewnum(view);
            data.setBvDmnum(dm);
        } else {
            data.setBvLikenum(0L);
            data.setBvCoinnum(0L);
            data.setBvCollectnum(0L);
            data.setBvSharenum(0L);
            data.setBvViewnum(0L);
            data.setBvDmnum(0L);
        }
        return bVideoDataService.save(data);
    }

    /**
     * 爬取视频数据，这里要写一个sql 就是获取视频数据小于7次的，然后就获得bv号就行了，超过7次以后就不爬取了,重复的就不用了
     */
    public List<BVideoData> getVideoDataList() throws Exception {
        List<BVideoData> list = bVideoDataService.list(new QueryWrapper<BVideoData>().lambda().groupBy(BVideoData::getBvNumber).having("COUNT(BV_NUMBER) < 7 "));
        List<BVideoData> list2 = new ArrayList<>();
        int flag = 0;
        for (BVideoData bVideoData : list) {
            getVideoData(bVideoData);
            log.info("flag=>" + flag++);
        }
        return list2;
    }


}
