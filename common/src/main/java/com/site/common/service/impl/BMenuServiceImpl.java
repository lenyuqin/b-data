package com.site.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.BMenu;
import com.site.common.mapper.BMenuMapper;
import com.site.common.service.BMenuService;
import org.springframework.stereotype.Service;

/**
 * (BMenu)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-27 15:56:13
 */
@DS("bdata")
@Service("bMenuService")
public class BMenuServiceImpl extends ServiceImpl<BMenuMapper, BMenu> implements BMenuService {

}