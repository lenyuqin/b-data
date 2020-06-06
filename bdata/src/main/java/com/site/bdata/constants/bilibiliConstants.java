package com.site.bdata.constants;

public interface bilibiliConstants {
    /**
     * 排行榜视频前缀
     */
    String RANK_URL = "https://www.bilibili.com/ranking";


    /**
     * 视频地址前缀
     *
     */
    String VIDEO_URL = "https://www.bilibili.com/video/";
    /**
     *
     * 保存视频的条数
     */
    Integer VIDEO_DATA_NUMBER = 7;


    /**
     * 视频分区的数字
     * 分区号  0(全站) 1(动画) 168(国创相关)
     * 3(音乐) 129(舞蹈) 4(游戏) 36(科技) 188(数码)
     * 160(生活),119(鬼畜),155(时尚),5(娱乐) 181(影视)
     */
    Integer BV_RANKZONE__ALL=0;
    Integer BV_RANKZONE__ANIMATION=1;
    Integer BV_RANKZONE__AIMTRON=168;
    Integer BV_RANKZONE__MUSIC=3;
    Integer BV_RANKZONE__DANCE=129;
    Integer BV_RANKZONE__GAME=4;
    Integer BV_RANKZONE__TECHNOLOGY=36;
    Integer BV_RANKZONE__DIGITAL=188;
    Integer BV_RANKZONE__LIFE=160;
    Integer BV_RANKZONE__DEVIL=119;
    Integer BV_RANKZONE__FASHION=155;
    Integer BV_RANKZONE__ENTERTAINMENT=5;
    Integer BV_RANKZONE_FILM =181;

}
