package com.site.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.common.entity.SysRole;
import com.site.common.mapper.SysRoleMapper;
import com.site.common.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * (SysRole)表服务实现类
 *
 * @author lenyuqin
 * @since 2020-12-23 19:57:42
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}