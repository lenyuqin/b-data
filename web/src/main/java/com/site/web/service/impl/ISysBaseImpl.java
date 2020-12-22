package com.site.web.service.impl;

import com.site.web.domain.Logging;
import com.site.web.plugins.logging.enums.BusinessType;
import com.site.web.plugins.logging.enums.LoggingType;
import com.site.web.service.LoggingService;
import com.site.web.plugins.system.domain.SysPowerModel;
import com.site.web.plugins.system.domain.SysRoleModel;
import com.site.web.plugins.system.domain.SysUserModel;
import com.site.web.plugins.system.service.ISysBaseAPI;
import com.site.web.utils.sequence.SequenceUtil;
import com.site.web.domain.SysPower;
import com.site.web.domain.SysRole;
import com.site.web.domain.SysUser;
import com.site.web.mapper.SysPowerMapper;
import com.site.web.mapper.SysRoleMapper;
import com.site.web.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 系统配置服务接口实现
 *
 * @author lenyuqin
 */
@Service
public class ISysBaseImpl implements ISysBaseAPI {
    @Resource
    LoggingService loggingService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysPowerMapper sysPowerMapper;

    @Override
    public void addLog(String title, String description, LoggingType logType, BusinessType operatetype) {
        // 创 建 实 例
        Logging logging = new Logging();
        // 日 志 编 号
        logging.setId(SequenceUtil.makeStringId());
        // 模 块 标 题
        logging.setTitle(title);
        // 模 块 描 述
        logging.setDescription(description);
        // 业 务 类 型
        logging.setBusinessType(operatetype);
        // 是 否 成 功
        logging.setSuccess(true);
        // 日 志 类 型
        logging.setLoggingType(logType);
        // 记 录 日 志
        loggingService.save(logging);
    }

    @Override
    public SysUserModel getUserByName(String username) {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        SysUserModel sysUserModel = null;
        if (sysUser != null) {
            sysUserModel = new SysUserModel();
            BeanUtils.copyProperties(sysUser, sysUserModel);
            List<SysPower> powerList = sysPowerMapper.selectByUsername(username);
            if ((powerList != null) && (powerList.size() > 0)) {
                List<SysPowerModel> sysPowerModelList = new ArrayList<>();
                for (SysPower sysPower : powerList) {
                    try {
                        SysPowerModel sysPowerModel = new SysPowerModel();
                        BeanUtils.copyProperties(sysPower, sysPowerModel);
                        sysPowerModelList.add(sysPowerModel);
                    } catch (Exception e) {

                    }
                }
                sysUserModel.setPowerList(sysPowerModelList);
            }

        }
        return sysUserModel;
    }

    @Override
    public SysUserModel getUserById(String id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        SysUserModel sysUserModel = null;
        if (sysUser != null) {
            sysUserModel = new SysUserModel();
            BeanUtils.copyProperties(sysUser, sysUserModel);
        }
        return sysUserModel;
    }

    @Override
    public List<SysRoleModel> getRolesByUsername(String username) {
        List<SysRole> roles = sysRoleMapper.selectByUsername(username);
        List<SysRoleModel> sysRoleModelList = new ArrayList<>();
        if ((roles != null) && (roles.size() > 0)) {
            for (SysRole sysRole : roles) {
                try {
                    SysRoleModel sysRoleModel = new SysRoleModel();
                    BeanUtils.copyProperties(sysRole, sysRoleModel);
                    sysRoleModelList.add(sysRoleModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sysRoleModelList;
    }


}
