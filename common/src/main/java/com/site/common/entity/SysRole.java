package com.site.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysRole)实体类
 *
 * @author lmk
 * @since 2020-12-23 19:57:41
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -59124850396722017L;
    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 是否启用
     */
    private String enable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 详情
     */
    private String details;

    /**
     * 排序
     */
    private Integer sort;

}