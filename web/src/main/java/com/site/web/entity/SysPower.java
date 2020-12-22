package com.site.web.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysPower)实体类
 *
 * @author lmk
 * @since 2020-12-20 22:44:20
 */
@Data
public class SysPower implements Serializable {
    private static final long serialVersionUID = -33012718152164098L;
    /**
     * 权限编号
     */
    private String powerId;

    /**
     * 权限名称
     */
    private String powerName;

    /**
     * 权限类型
     */
    private String powerType;

    /**
     * 权限标识
     */
    private String powerCode;

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

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否开启
     */
    private String enable;

}