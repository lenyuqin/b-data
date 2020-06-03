package com.site.bdata.entity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;

/**
 * (BVideoRank)实体类
 *
 * @author lmk
 * @since 2020-06-03 15:55:23
 */
@Data
@ApiModel("$tableInfo.comment")
public class BVideoRank  implements Serializable {
    private static final long serialVersionUID = 297982530075757419L;
    /**
    * 视频BV号
    */    
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
    * 视频得分
    */    
    @ApiModelProperty("视频得分")
    private String bvScore;
    
    /**
    * 视频分区的数字
    */    
    @ApiModelProperty("视频分区的数字")
    private Integer bvRankzone;
    
    /**
    * 视频排名
    */    
    @ApiModelProperty("视频排名")
    private Integer bvRanknum;
    
    /**
    * up主uuid号
    */    
    @ApiModelProperty("up主uuid号")
    private String bvUpuuid;
    
}