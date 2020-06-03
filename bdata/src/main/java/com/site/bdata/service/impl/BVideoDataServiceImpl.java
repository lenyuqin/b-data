package com.site.bdata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.bdata.entity.BVideoData;
import com.site.bdata.mapper.BVideoDataMapper;
import com.site.bdata.service.BVideoDataService;
import org.springframework.stereotype.Service;

/**
 * (BVideoData)表服务实现类
 *
 * @author lmk
 * @since 2020-06-03 10:19:24
 */
@Service("bVideoDataService")
public class BVideoDataServiceImpl extends ServiceImpl<BVideoDataMapper, BVideoData> implements BVideoDataService {

}