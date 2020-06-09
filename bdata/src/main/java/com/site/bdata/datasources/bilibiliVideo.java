package com.site.bdata.datasources;


import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.bdata.constants.bilibiliConstants;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoHistoryService;
import com.site.bdata.service.BVideoRankService;
import com.site.bdata.util.DateUtils;
import com.site.bdata.util.jsoupUtil;
import com.site.bdata.util.numberUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Component
public class bilibiliVideo {
    @Resource
    private BVideoRankService bVideoRankService;
    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoDataService bVideoDataService;

    private static bilibiliVideo dataUpload;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        dataUpload = this;
        dataUpload.bVideoRankService = this.bVideoRankService;
        dataUpload.bVideoHistoryService = this.bVideoHistoryService;
        dataUpload.bVideoDataService = this.bVideoDataService;
        // 初使化时将已静态化的testService实例化
    }
    public static BVideoData BVdata(BVideoHistory bVideoHistory){
        /**
         * 点赞数193800
         * 13.4万
         * 12.4万
         * 2.3万 动态 微博 QQ QQ空间 贴吧 将视频贴到博客或论坛 视频地址 复制 嵌入代码 复制 微信扫一扫分享
         * 总播放数2216846
         * 历史累计弹幕数13280
         *
         *
         * {"now_rank":0,"evaluation":"",
         * "view":43891,"like":4405,"dislike":0,
         * "danmaku":406,"share":156,"reply":324,
         * "his_rank":0,"aid":710932975,"favorite":877,
         * "coin":3419}
         * */

        //先查询是否有当天的信息,有的话就直接返回这个对象,没有再去查找
        Timestamp dateBegin = Timestamp.valueOf(bilibiliConstants.DATE_BEGIN);
        Timestamp dateEnd = Timestamp.valueOf(bilibiliConstants.DATE_END);
        List<BVideoData> list = dataUpload.bVideoDataService.list(new QueryWrapper<BVideoData>()
                .lambda().between(BVideoData::getBvTime, dateBegin, dateEnd).eq(BVideoData::getBvNumber, bVideoHistory.getBvNumber()));
//        先查询是否有当天的记录,有的话就不用查询了
        if (list.isEmpty()){
            String body = HttpRequest.get(bilibiliConstants.VIDEO_API_URL+bVideoHistory.getBvNumber()).timeout(2000).execute().body();
            String code = JSON.parseObject(body).getString("code");
            BVideoData bVideoData = new BVideoData();
            bVideoData.setBvNumber(bVideoHistory.getBvNumber());
            bVideoData.setBvUp(bVideoHistory.getBvUp());
            bVideoData.setBvUpuuid(bVideoHistory.getBvUpuuid());
            bVideoData.setBvTitle(bVideoHistory.getBvTitle());
            bVideoData.setBvTime(DateUtils.getLocalCurrentDate());
            if("0".equals(code)){
                JSONObject VideoData = JSON.parseObject(body).getJSONObject("data").getJSONObject("stat");
                Long reply=VideoData.getLong("reply");
                bVideoData.setBvCoinnum(VideoData.getLong("coin"));
                bVideoData.setBvCollectnum( VideoData.getLong("favorite"));
                bVideoData.setBvDmnum(VideoData.getLong("danmaku"));
                bVideoData.setBvLikenum(VideoData.getLong("like"));
                bVideoData.setBvViewnum(VideoData.getLong("view"));
                bVideoData.setBvSharenum(VideoData.getLong("share"));
            }else {
                log.error("================"+bVideoHistory.getBvNumber()+"视频不存在"+"================");
                bVideoData.setBvCoinnum((long) 0);
                bVideoData.setBvCollectnum((long) 0);
                bVideoData.setBvDmnum((long) 0);
                bVideoData.setBvLikenum((long) 0);
                bVideoData.setBvViewnum((long) 0);
                bVideoData.setBvSharenum((long) 0);
            }
            return bVideoData;
        }else {
            return list.get(0);
        }




    }

}
