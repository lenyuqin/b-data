package com.site.datasourse.constants;

import cn.hutool.core.date.DateUtil;

public interface bilibiliConstants {
    /**
     * 排行榜视频前后缀
     */
    String RANK_URL_PREFIX = "https://www.bilibili.com/ranking/";
    String RANK_URL_SUFFIX = "/0/3";

    /**
     * 视频地址前缀
     */
    String VIDEO_URL = "https://www.bilibili.com/video/";
    /**
     * 视频api地址
     */
    String VIDEO_API_URL = "http://api.bilibili.com/x/web-interface/view?bvid=";
    /**
     * 保存视频的条数
     */
    Integer VIDEO_DATA_NUMBER = 7;

    /**
     * 记录视频条数的flag
     */
    Integer VIDEO_DATA_FLAG = 1;
    

    /**
     * 视频排行榜请求数据地址
     */
    String VIDEO_LEADERBOARD_REQUEST_PREFIX = "https://api.bilibili.com/x/web-interface/ranking/v2?rid=";


    /**
     * 作者点赞数和视频播放数总和
     */
    String UP_LIKES_AND_VIEWS = "https://api.bilibili.com/x/space/upstat?mid=99336697";

    /**
     * 作者粉丝数总和
     */
    String UP_FOLLOWS_NUM = "https://api.bilibili.com/x/relation/stat?vmid=130636947";


    /**
     * 热门数据排行榜
     */
    String HOT_VIDEO_REQUEST = "https://api.bilibili.com/x/web-interface/popular?ps=50&pn=";


    /**
     * 时间查询时出现的问题:前端返回数据为"2020-06-05" 要在后面加上这两个词尾,就可以实现按天查询(用的是between)
     */
    String DATE_BEGIN = " 00:00:0";
    String DATE_END = " 23:59:59";
    String TODAY_DATE_END = DateUtil.today() + " 23:59:59";
    String TODAY_DATE_BEGIN = DateUtil.today() + " 00:00:0";
    String FORMAT = "yyyy-MM-dd";

}
