package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BHomePageData;
import com.site.common.mapper.BHomePageDataMapper;
import com.site.common.service.BHomePageDataService;
import org.springframework.stereotype.Service;

/**
 * (BHomePageData)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-22 11:55:26
 */
@DS("bdata")
@Service("bHomePageDataService")
public class BHomePageDataServiceImpl extends ServiceImpl<BHomePageDataMapper, BHomePageData> implements BHomePageDataService {

}