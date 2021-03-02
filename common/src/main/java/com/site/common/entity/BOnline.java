package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BOnline)实体类
 *
 * @author lmk
 * @since 2021-01-31 15:40:38
 */
@Data
public class BOnline implements Serializable {
    private static final long serialVersionUID = -59898622983905073L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 视频排名
     */
    private Integer bvRank;

    /**
     * 爬取时间
     */
    private Date bvDate;

    /**
     * 视频bv号
     */
    private String bvNumber;

    /**
     * 视频标题
     */
    private String bvTitle;

    /**
     * 视频在线人数
     */
    private Integer bvOnline;

    /**
     * 视频作者
     */
    private String bvAuthor;

    /**
     * 视频up的id
     */
    private String bvUpuuid;

    /**
     * 视频封面
     */
    private String bvImg;

}