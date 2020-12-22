package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BVideoData;
import com.site.common.mapper.BVideoDataMapper;
import com.site.common.service.BVideoDataService;
import org.springframework.stereotype.Service;

/**
 * (BVideoData)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-21 15:53:09
 */
@DS("bdata")
@Service("bVideoDataService")
public class BVideoDataServiceImpl extends ServiceImpl<BVideoDataMapper, BVideoData> implements BVideoDataService {

}