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
 * @since 2020-06-03 15:55:12
 */
@Data
@ApiModel("搜索视频的历史")
public class BVideoHistory  implements Serializable {
    private static final long serialVersionUID = -23217740271573414L;
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
    * up主uuid号
    */    
    @ApiModelProperty("up主uuid号")
    private String bvUpuuid;
    
}