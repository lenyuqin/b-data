package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author lmk
 * @since 2020-12-23 19:58:13
 */
@Data
public class SysUser implements Serializable, UserDetails {
    private static final long serialVersionUID = 353612025660935247L;
    /**
     * 编号
     */
    @TableId
    private String userId;

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String salt;

    /**
     * 状态
     */
    private String status;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话
     */
    private String phone;

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
     * 是否启用
     */
    private String enable;

    /**
     * 是否登录
     */
    private String login;

    /**
     * 部门编号
     */
    private String deptId;

    /**
     * 最后一次登录时间
     */
    private Date lastTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}