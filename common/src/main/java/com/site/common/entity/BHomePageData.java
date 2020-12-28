package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * (BHomePageData)实体类
 *
 * @author lmk
 * @since 2020-12-22 11:55:20
 */
@Data
public class BHomePageData implements Serializable {
    private static final long serialVersionUID = -96496146949200716L;
    /**
     * 总访问量
     */
    private Integer bvTotalVisits;

    /**
     * 日期（按天算）
     */
    private Date bvDay;

    /**
     * 当天访问量
     */
    private Integer bvDayVisits;

    /**
     * 视频总数
     */
    private Integer bvTotalVideo;

    /**
     * up主总数
     */
    private Integer bvTotalUp;

    /**
     * 历史访问量（不重复）
     */
    private Integer bvHistoryVisits;

}