package com.site.bdata.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import lombok.Data;

/**
 * (BVideoRank)实体类
 *
 * @author lmk
 * @since 2020-06-03 16:03:15
 */
@Data
@ApiModel("视频排行榜")
public class BVideoRank  implements Serializable {
    private static final long serialVersionUID = -91303092708928109L;
    /**
    * 视频BV号
    */
    @TableField("BV_NUMBER")
    @ApiModelProperty("视频BV号")
    private String bvNumber;
    
    /**
    * up主名字
    */
    @TableField("BV_UP")
    @ApiModelProperty("up主名字")
    private String bvUp;
    
    /**
    * 视频标题
    */
    @TableField("BV_TITLE")
    @ApiModelProperty("视频标题")
    private String bvTitle;
    
    /**
    * 视频得分
    */
    @TableField("BV_SCORE")
    @ApiModelProperty("视频得分")
    private String bvScore;
    
    /**
    * 视频分区的数字
     * 分区号  0(全站) 1(动画) 168(国创相关) 3(音乐) 129(舞蹈) 4(游戏) 36(科技) 188(数码) 160(生活),119(鬼畜),155(时尚),5(娱乐) 181(影视)
    */
    @TableField("BV_RANKZONE")
    @ApiModelProperty("视频分区的数字")
    private Integer bvRankzone;
    
    /**
    * 视频排名
    */
    @TableField("BV_RANKNUM")
    @ApiModelProperty("视频排名")
    private Integer bvRanknum;
    
    /**
    * up主uuid号
    */
    @TableField("BV_UPUUID")
    @ApiModelProperty("up主uuid号")
    private String bvUpuuid;
    
    /**
    * 搜索视频的时间
    */
    @TableField("BV_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("搜索视频的时间")
    private Date bvTime;
    
}