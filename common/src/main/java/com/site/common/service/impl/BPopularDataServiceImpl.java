package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BPopularData;
import com.site.common.mapper.BPopularDataMapper;
import com.site.common.service.BPopularDataService;
import org.springframework.stereotype.Service;

/**
 * (BPopularData)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-23 13:11:25
 */
@DS("bdata")
@Service("bPopularDataService")
public class BPopularDataServiceImpl extends ServiceImpl<BPopularDataMapper, BPopularData> implements BPopularDataService {

}