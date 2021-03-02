package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BAuthorData;
import com.site.common.mapper.BAuthorDataMapper;
import com.site.common.service.BAuthorDataService;
import org.springframework.stereotype.Service;

/**
 * (BAuthorData)表服务实现类
 *
 * @author lenyuqin
 * @since 2021-01-03 13:05:23
 */
@DS("bdata")
@Service("bAuthorDataService")
public class BAuthorDataServiceImpl extends ServiceImpl<BAuthorDataMapper, BAuthorData> implements BAuthorDataService {

}