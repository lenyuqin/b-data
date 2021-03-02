package com.site.web.service;

import com.site.common.entity.BVideoData;
import com.site.web.entity.Card;

import java.util.List;

/**
 * @author lenyuqin
 * @data 2021/1/1
 */
public interface CardService {

    /**
     * 得到最新的播放量最高的视频数据 要进行分页
     * @return List
     */
    List<Card> getPagesVideo();


    /**
     * 得到最新热门榜播放量最高的视频数据 要进行分页
     * @return List
     */
    List<Card> getPopularPagesVideo();



}
