package com.site.modules.system.security.domain;

import com.site.modules.system.domain.SysPower;
import com.site.modules.system.domain.SysUser;
import com.site.modules.system.mapper.SysPowerMapper;
import com.site.modules.system.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: Security 用户服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysPowerMapper sysPowerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if(sysUser==null){
            throw new UsernameNotFoundException("Account Not Found");
        }
        List<SysPower> powerList = sysPowerMapper.selectByUsername(username);
        sysUser.setPowerList(powerList);
        return sysUser;
    }
}
