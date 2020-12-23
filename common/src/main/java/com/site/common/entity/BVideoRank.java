package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BVideoRank)实体类
 *
 * @author lmk
 * @since 2020-12-21 15:53:48
 */
@Data
public class BVideoRank implements Serializable {
    private static final long serialVersionUID = -31866116653397078L;
    /**
     * 视频BV号
     */
    @TableId
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
     * 视频得分
     */
    private String bvScore;

    /**
     * 视频分区的数字
     */
    private Integer bvRankzone;

    /**
     * 视频排名
     */
    private Integer bvRanknum;

    /**
     * up主uuid号
     */
    private String bvUpuuid;

    /**
     * 搜索视频的时间
     */
    private Date bvTime;

}