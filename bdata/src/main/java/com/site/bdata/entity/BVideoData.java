package com.site.bdata.entity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;

/**
 * (BVideoData)实体类
 *
 * @author lmk
 * @since 2020-06-03 10:19:20
 */
@Data
@ApiModel("$tableInfo.comment")
public class BVideoData  implements Serializable {
    private static final long serialVersionUID = 218388215002727981L;
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
    * 评论数量
    */    
    @ApiModelProperty("评论数量")
    private Long bvCommentnum;
    
}