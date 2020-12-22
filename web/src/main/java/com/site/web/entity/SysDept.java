package com.site.web.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysDept)实体类
 *
 * @author lmk
 * @since 2020-12-20 22:43:50
 */
@Data
public class SysDept implements Serializable {
    private static final long serialVersionUID = 690820087602936819L;
    /**
     * 部门名称
     */
    private String deptId;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态
     */
    private String status;

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
     * 详细地址
     */
    private String address;

}