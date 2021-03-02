package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BCarousel;
import com.site.common.mapper.BCarouselMapper;
import com.site.common.service.BCarouselService;
import org.springframework.stereotype.Service;

/**
 * (BCarousel)表服务实现类
 *
 * @author lenyuqin
 * @since 2021-02-25 22:53:18
 */
@DS("bdata")
@Service("bCarouselService")
public class BCarouselServiceImpl extends ServiceImpl<BCarouselMapper, BCarousel> implements BCarouselService {

}