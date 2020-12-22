package com.site.web.mapper;

import com.site.web.domain.SysRolePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Describe: 角色权限接口
 * todo 这个是单表操作，可以用mybatis-plus
 * */
@Mapper
public interface SysRolePowerMapper {

    List<SysRolePower> selectByRoleId(String roleId);

    int batchInsert(List<SysRolePower> sysRolePowers);

    int deleteByRoleId(String roleId);

    int deleteByRoleIds(String[] roleIds);

    int deleteByPowerId(String powerId);

    int deleteByPowerIds(String[] powerIds);
}
