package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BVideoRank;
import com.site.common.mapper.BVideoRankMapper;
import com.site.common.service.BVideoRankService;
import org.springframework.stereotype.Service;

/**
 * (BVideoRank)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-21 15:53:49
 */
@DS("bdata")
@Service("bVideoRankService")
public class BVideoRankServiceImpl extends ServiceImpl<BVideoRankMapper, BVideoRank> implements BVideoRankService {

}