package com.site.web.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysRolePower)实体类
 *
 * @author lmk
 * @since 2020-12-20 22:44:55
 */
@Data
public class SysRolePower implements Serializable {
    private static final long serialVersionUID = 795801813921563878L;
    private String id;

    private String roleId;

    private String powerId;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}