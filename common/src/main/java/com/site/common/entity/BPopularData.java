package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BPopularData)实体类
 *
 * @author lmk
 * @since 2020-12-23 13:11:24
 */
@Data
public class BPopularData implements Serializable {
    private static final long serialVersionUID = -10488137874651142L;
    /**
     * 视频bv号
     */
    private String bvNumber;

    /**
     * 视频标题
     */
    private String bvTitle;

    /**
     * 视频封面图片url
     */
    private String bvPicUrl;

    /**
     * 视频up主
     */
    private String bvUp;

    /**
     * 视频播放量
     */
    private Long bvView;

    /**
     * 视频点赞
     */
    private Long bvLike;

    /**
     * 视频硬币
     */
    private Long bvCoin;

    /**
     * 视频弹幕
     */
    private Long bvDanmaku;

    /**
     * 视频收藏
     */
    private Long bvFavorite;
    /**
     * 视频评论
     */
    private Long bvReply;

    /**
     * 视频分享
     */
    private Long bvShare;

    /**
     * 视频up主id
     */
    private String bvUpuuid;

    /**
     * 视频描述
     */
    private String bvDesc;

    /**
     * 视频up主封面
     */
    private String bvUpFace;

    /**
     * 视频排名
     */
    private Integer bvRank;

    /**
     * 搜索视频的时间
     */
    private Date bvTime;

}