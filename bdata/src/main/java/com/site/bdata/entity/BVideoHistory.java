package com.site.bdata.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;

/**
 * (BVideoHistory)实体类
 *
 * @author lmk
 * @since 2020-06-03 10:43:35
 */
@Data
@ApiModel("$tableInfo.comment")
public class BVideoHistory  implements Serializable {
    private static final long serialVersionUID = 255130256793871656L;
    /**
    * 视频BV号
    */
    @TableId
    @ApiModelProperty("视频BV号")
    private String bvNumber;
    
    /**
    * up主名字
    */    
    @ApiModelProperty("up主名字")
    private String bvUp;
    
    /**
    * 视频标题
    */    
    @ApiModelProperty("视频标题")
    private String bvTitle;
    
    /**
    * 封面图片地址
    */    
    @ApiModelProperty("封面图片地址")
    private String bvImgAddress;
    
}