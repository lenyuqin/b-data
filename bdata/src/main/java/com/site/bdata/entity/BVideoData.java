package com.site.bdata.entity;

import java.util.Date;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import lombok.Data;

/**
 * (BVideoData)实体类
 *
 * @author lmk
 * @since 2020-06-03 16:03:05
 */
@Data
@ApiModel("视频数据")
public class BVideoData  implements Serializable {
    private static final long serialVersionUID = -90297134015885810L;
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
    * 点赞数量
    */    
    @ApiModelProperty("点赞数量")
    private Long bvLikenum;
    
    /**
    * 硬币数量
    */    
    @ApiModelProperty("硬币数量")
    private Long bvCoinnum;
    
    /**
    * 收藏数量
    */    
    @ApiModelProperty("收藏数量")
    private Long bvCollectnum;
    
    /**
    * 分享数量
    */    
    @ApiModelProperty("分享数量")
    private Long bvSharenum;
    
    /**
    * 播放量
    */    
    @ApiModelProperty("播放量")
    private Long bvViewnum;
    
    /**
    * 弹幕数量
    */    
    @ApiModelProperty("弹幕数量")
    private Long bvDmnum;

    
    /**
    * up主UUID
    */    
    @ApiModelProperty("up主UUID")
    private String bvUpuuid;
    
    /**
    * 搜索视频的时间
    */
    @ApiModelProperty("搜索视频的时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bvTime;
    
}