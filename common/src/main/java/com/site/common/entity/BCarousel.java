package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (BCarousel)实体类
 *
 * @author lmk
 * @since 2021-02-25 22:53:07
 */
@Data
public class BCarousel implements Serializable {
    private static final long serialVersionUID = 765872663974415776L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 爬取时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 标题
     */
    private String title;

}