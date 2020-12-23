package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (BAuthorBasedata)实体类
 *
 * @author lmk
 * @since 2020-12-21 15:52:42
 */
@Data
public class BAuthorBasedata implements Serializable {
    private static final long serialVersionUID = -65264593752892001L;
    /**
     * 作者的UUID（唯一）
     */
    @TableId
    private Long bvUpuuid;

    /**
     * 作者的粉丝数量
     */
    private Long bvUpFollower;

    /**
     * 作者的总获得点赞数
     */
    private Long bvUpLikes;

    /**
     * 作者视频的总播放数
     */
    private Long bvUpView;

    /**
     * 作者的头像
     */
    private String bvFaceUrl;

    /**
     * 作者的昵称
     */
    private String bvName;



}