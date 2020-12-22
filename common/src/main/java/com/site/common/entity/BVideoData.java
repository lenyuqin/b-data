package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BVideoData)实体类
 *
 * @author lmk
 * @since 2020-12-21 15:53:06
 */
@Data
public class BVideoData implements Serializable {
    private static final long serialVersionUID = -14258867591423492L;
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

}