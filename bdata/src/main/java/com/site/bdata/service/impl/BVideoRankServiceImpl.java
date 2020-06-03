package com.site.bdata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.bdata.mapper.BVideoRankMapper;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoRankService;
import org.springframework.stereotype.Service;

/**
 * (BVideoRank)表服务实现类
 *
 * @author lmk
 * @since 2020-06-03 10:20:01
 */
@Service("bVideoRankService")
public class BVideoRankServiceImpl extends ServiceImpl<BVideoRankMapper, BVideoRank> implements BVideoRankService {

}