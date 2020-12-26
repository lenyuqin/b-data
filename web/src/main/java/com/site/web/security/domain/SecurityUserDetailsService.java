package com.site.web.security.domain;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.SysPower;
import com.site.common.entity.SysUser;
import com.site.common.service.SysPowerService;
import com.site.common.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: Security 用户服务
 * 这个也不能删除
 *
 * @author lenyuqin
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysPowerService sysPowerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            throw new UsernameNotFoundException("Account Not Found");
        }
        return sysUser;
    }
}
