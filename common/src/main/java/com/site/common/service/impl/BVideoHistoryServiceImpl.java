package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BVideoHistory;
import com.site.common.mapper.BVideoHistoryMapper;
import com.site.common.service.BVideoHistoryService;
import org.springframework.stereotype.Service;

/**
 * (BVideoHistory)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-21 15:53:30
 */
@DS("bdata")
@Service("bVideoHistoryService")
public class BVideoHistoryServiceImpl extends ServiceImpl<BVideoHistoryMapper, BVideoHistory> implements BVideoHistoryService {

}