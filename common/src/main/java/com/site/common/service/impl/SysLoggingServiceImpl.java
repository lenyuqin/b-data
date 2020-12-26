package com.site.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.SysLogging;
import com.site.common.mapper.SysLoggingMapper;
import com.site.common.service.SysLoggingService;
import org.springframework.stereotype.Service;

/**
 * (SysLogging)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-23 19:57:06
 */
@Service("sysLoggingService")
public class SysLoggingServiceImpl extends ServiceImpl<SysLoggingMapper, SysLogging> implements SysLoggingService {

}