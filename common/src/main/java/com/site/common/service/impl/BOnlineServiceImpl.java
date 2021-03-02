package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BOnline;
import com.site.common.mapper.BOnlineMapper;
import com.site.common.service.BOnlineService;
import org.springframework.stereotype.Service;

/**
 * (BOnline)表服务实现类
 *
 * @author lenyuqin
 * @since 2021-01-31 15:40:43
 */
@DS("bdata")
@Service("bOnlineService")
public class BOnlineServiceImpl extends ServiceImpl<BOnlineMapper, BOnline> implements BOnlineService {

}