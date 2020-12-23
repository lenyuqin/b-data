package com.site.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (BVideoHistory)实体类
 *
 * @author lmk
 * @since 2020-12-21 15:53:30
 */
@Data
public class BVideoHistory implements Serializable {
    private static final long serialVersionUID = -48186310393752464L;
    /**
     * 视频BV号
     */
    @TableId
    private String bvNumber;

    /**
     * up主名字
     */
    private String bvUp;

    /**
     * 视频标题
     */
    private String bvTitle;

    /**
     * up主uuid号
     */
    private String bvUpuuid;

}