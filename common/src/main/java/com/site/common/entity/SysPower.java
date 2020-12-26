package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysPower)实体类
 *
 * @author lmk
 * @since 2020-12-23 19:57:24
 */
@Data
public class SysPower implements Serializable {
    private static final long serialVersionUID = 987517846471504634L;


    /**
     * 权限名称
     */
    private String powerName;

    /**
     * 权限路径
     */
    private String powerUrl;

    /**
     * 打开方式
     */
    private String openType;

    /**
     * 父类编号
     */
    private String parentId;

    /**
     * 图标
     */
    private String icon;


}