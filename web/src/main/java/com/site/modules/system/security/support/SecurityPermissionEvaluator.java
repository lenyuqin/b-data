package com.site.modules.system.security.support;

import com.site.modules.system.config.proprety.SecurityProperty;
import com.site.modules.system.domain.SysPower;
import com.site.modules.system.domain.SysUser;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describe: 自定义 Security 权限注解实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecurityPermissionEvaluator implements PermissionEvaluator {

    @Resource
    private SecurityProperty securityProperty;

    /**
     * Describe: 自定义 Security 权限认证 @HasPermission
     * Param: Authentication
     * Return Boolean
     * */
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1)
    {
        SysUser securityUserDetails = (SysUser) authentication.getPrincipal();
        if (securityProperty.isSuperAuthOpen() && securityProperty.getSuperAdmin().equals(securityUserDetails.getUsername())) {
            return true;
        }
        List<SysPower> powerList = securityUserDetails.getPowerList();
        Set<String> permissions = new HashSet<>();
        for (SysPower sysPower :powerList) {
            permissions.add(sysPower.getPowerCode());
        }
        if(permissions.contains(o1)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
