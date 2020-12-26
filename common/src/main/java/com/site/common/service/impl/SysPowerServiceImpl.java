package com.site.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.SysPower;
import com.site.common.mapper.SysPowerMapper;
import com.site.common.service.SysPowerService;
import org.springframework.stereotype.Service;

/**
 * (SysPower)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-23 19:57:25
 */
@Service("sysPowerService")
public class SysPowerServiceImpl extends ServiceImpl<SysPowerMapper, SysPower> implements SysPowerService {

}