package com.site.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.SysUser;
import com.site.common.mapper.SysUserMapper;
import com.site.common.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * (SysUser)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-23 19:58:14
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}