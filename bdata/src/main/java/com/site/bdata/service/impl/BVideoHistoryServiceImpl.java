package com.site.bdata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.mapper.BVideoHistoryMapper;
import com.site.bdata.service.BVideoHistoryService;
import org.springframework.stereotype.Service;

/**
 * (BVideoHistory)表服务实现类
 *
 * @author lmk
 * @since 2020-06-03 10:19:48
 */
@Service("bVideoHistoryService")
public class BVideoHistoryServiceImpl extends ServiceImpl<BVideoHistoryMapper, BVideoHistory> implements BVideoHistoryService {

}