package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (BAuthorData)实体类
 *
 * @author lmk
 * @since 2021-01-03 13:05:21
 */
@Data
public class BAuthorData implements Serializable {
    private static final long serialVersionUID = -34159837508032468L;
    /**
     * 作者的UUID（唯一）
     */
    @TableId
    private Integer bvUpuuid;

    /**
     * 作者的粉丝数量
     */
    private Integer bvUpFollower;

    /**
     * 作者的总获得点赞数
     */
    private Integer bvUpLikes;

    /**
     * 作者视频的总播放数
     */
    private Integer bvUpView;

}