package com.site.bdata.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (BAuthorBasedata)实体类
 *
 * @author lmk
 * @since 2020-11-30 14:21:46
 */
@Data
@ApiModel("$tableInfo.comment")
public class BAuthorBasedata implements Serializable {
    private static final long serialVersionUID = -17523363016509772L;
    /**
     * 作者的UUID（唯一）
     */
    @ApiModelProperty("作者的UUID（唯一）")
    private Long bvUpuuid;

    /**
     * 作者的粉丝数量
     */
    @ApiModelProperty("作者的粉丝数量")
    private Long bvUpFollower;

    /**
     * 作者的总获得点赞数
     */
    @ApiModelProperty("作者的总获得点赞数")
    private Long bvUpLikes;

    /**
     * 作者视频的总播放数
     */
    @ApiModelProperty("作者视频的总播放数")
    private Long bvUpView;

}