package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (BMenu)实体类
 *
 * @author lmk
 * @since 2020-12-27 15:56:12
 */
@Data
public class BMenu implements Serializable {
    private static final long serialVersionUID = 695074311992345729L;
    /**
     * 菜单id
     */
    private String id;

    /**
     * 菜单名字
     */
    private String title;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 菜单url
     */
    private String href;

    /**
     * 菜单打开类型
     */
    private String opentype;

    /**
     * 父菜单id
     */
    private String parentid;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private String sort;

}