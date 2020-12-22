package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BAuthorBasedata;
import com.site.common.mapper.BAuthorBasedataMapper;
import com.site.common.service.BAuthorBasedataService;
import org.springframework.stereotype.Service;

/**
 * (BAuthorBasedata)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-21 15:52:47
 */
@DS("bdata")
@Service("bAuthorBasedataService")
public class BAuthorBasedataServiceImpl extends ServiceImpl<BAuthorBasedataMapper, BAuthorBasedata> implements BAuthorBasedataService {

}