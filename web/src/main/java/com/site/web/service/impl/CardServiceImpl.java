package com.site.web.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BVideoData;
import com.site.common.service.BVideoDataService;
import com.site.web.entity.Card;
import com.site.web.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenyuqin
 * @data 2021/1/1
 */
@Slf4j
@Service
public class CardServiceImpl implements CardService {
    @Resource
    private BVideoDataService bVideoDataService;


    /**
     * 得到最新的播放量最高的视频数据 要进行分页 这里分页我还没写
     *
     * @return List
     */
    @Override
    public List<Card> getPagesVideo() {
        List<BVideoData> bVideoDataList = bVideoDataService.list(new QueryWrapper<BVideoData>()
                .lambda().last("limit 20").orderByDesc(BVideoData::getBvTime));
        List<Card> cardList = bVideoDataList.stream().map(bVideoData -> {
            Card card = new Card();
            card.setTitle(bVideoData.getBvTitle());
            card.setId(bVideoData.getBvNumber());
            card.setImage(bVideoData.getBvPicUrl());
            card.setRemark(bVideoData.getBvDesc());
            card.setViews(String.valueOf(bVideoData.getBvViewnum()));
            card.setTime(DateUtil.format(bVideoData.getBvTime(), "yyyy/MM/dd"));
            return card;
        }).collect(Collectors.toList());
        return cardList;
    }

    /**
     * 得到最新热门榜播放量最高的视频数据 要进行分页
     *
     * @return List
     */
    @Override
    public List<Card> getPopularPagesVideo() {
        return null;
    }
}
