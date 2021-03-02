package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysUserRole)实体类
 *
 * @author lmk
 * @since 2020-12-23 19:58:36
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 738578335788561799L;
    /**
     * 标识
     */
    @TableId
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