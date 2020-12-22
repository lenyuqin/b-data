package com.site.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SysUserRole)实体类
 *
 * @author lmk
 * @since 2020-12-20 22:45:26
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -78688457785825638L;
    /**
     * 标识
     */
    private String id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 角色编号
     */
    private String roleId;

}