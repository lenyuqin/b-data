package com.site.web.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysConfig)实体类
 *
 * @author lmk
 * @since 2020-12-20 22:43:03
 */
@Data
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 401845427255958374L;
    /**
     * 配置标识
     */
    private String configId;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 配置标识
     */
    private String configCode;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

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
     * 配置类型
     */
    private String configType;

}