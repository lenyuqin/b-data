package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BVideoData)实体类
 *
 * @author lmk
 * @since 2020-12-23 12:13:55
 */
@Data
public class BVideoData implements Serializable {
    private static final long serialVersionUID = -29333051165115673L;
    /**
     * 视频BV号
     */
    private String bvNumber;

    /**
     * up主名字
     */
    private String bvUp;

    /**
     * 视频标题
     */
    private String bvTitle;

    /**
     * 点赞数量
     */
    private Long bvLikenum;

    /**
     * 硬币数量
     */
    private Long bvCoinnum;

    /**
     * 收藏数量
     */
    private Long bvCollectnum;

    /**
     * 评论的数量
     */
    private Integer bvReply;

    /**
     * 分享数量
     */
    private Long bvSharenum;

    /**
     * 播放量
     */
    private Long bvViewnum;

    /**
     * 弹幕数量
     */
    private Long bvDmnum;

    /**
     * up主UUID
     */
    private String bvUpuuid;

    /**
     * 搜索视频的时间
     */
    private Date bvTime;

    /**
     * 视频描述
     */
    private String bvDesc;

    /**
     * 视频封面图片url
     */
    private String bvPicUrl;

}