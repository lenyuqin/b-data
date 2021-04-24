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
    String UP_LIKES_AND_VIEWS = "https://api.bilibili.com/x/space/upstat?mid=";
    String JSONP = "&jsonp=jsonp";

    /**
     * 作者粉丝数总和
     */
    String UP_FOLLOWS_NUM = "https://api.bilibili.com/x/relation/stat?vmid=";

    String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36 Edg/88.0.705.81";

    //https://www.bilibili.com/video/online.html在线人数的视频


    /**
     * 热门数据排行榜
     */
    String HOT_VIDEO_REQUEST = "https://api.bilibili.com/x/web-interface/popular?ps=50&pn=";

    String MY_COOKIE = "buvid3=5476898B-BE34-421A-8393-5FF2732C9C8640757infoc; " +
            "LIVE_BUVID=AUTO6215619878787299; rpdid=|(k|k)kYm)ml0J'ulYk|~m~JR; " +
            "fts=1562166493; im_notify_type_20792517=0; dy_spec_agreed=1; " +
            "LIVE_PLAYER_TYPE=2; _uuid=74018EFF-2A72-EE59-E8B7-B1F1206F266875904infoc; " +
            "blackside_state=1; CURRENT_FNVAL=80; sid=bqo31pkp; PVID=6; DedeUserID=20792517; " +
            "DedeUserID__ckMd5=e6764de598801ed9; SESSDATA=99539cc0%2C1620911111%2C57d3d*b1; " +
            "bili_jct=a666b2c1771d5d70e12d91f17119dbca; fingerprint3=97093d56800cff061bade9114bb2415e; " +
            "buivd_fp=5476898B-BE34-421A-8393-5FF2732C9C8640757infoc; " +
            "buvid_fp_plain=5476898B-BE34-421A-8393-5FF2732C9C8640757infoc; " +
            "fingerprint=cb71b7d51bcc4f00a0b090b1234fc092; " +
            "fingerprint_s=b6885191db999511a525146532028f1d; " +
            "CURRENT_QUALITY=0; " +
            "bp_video_offset_20792517=497811482987231820; " +
            "bp_t_offset_20792517=497811482987231820";

    /**
     * 时间查询时出现的问题:前端返回数据为"2020-06-05" 要在后面加上这两个词尾,就可以实现按天查询(用的是between)
     */
    String DATE_BEGIN = " 00:00:0";
    String DATE_END = " 23:59:59";
    String TODAY_DATE_END = DateUtil.today() + " 23:59:59";
    String TODAY_DATE_BEGIN = DateUtil.today() + " 00:00:0";
    String FORMAT = "yyyy-MM-dd";


    String ONLINE_URL = "https://www.bilibili.com/video/online.html";
    String ONLINE_URL_PREFIX = "//www.bilibili.com/video/";
    String MAIN_WEBSITE = "https://www.bilibili.com/";

}
