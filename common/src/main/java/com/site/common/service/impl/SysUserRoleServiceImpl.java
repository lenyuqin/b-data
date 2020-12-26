package com.site.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.SysUserRole;
import com.site.common.mapper.SysUserRoleMapper;
import com.site.common.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * (SysUserRole)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-23 19:58:36
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}