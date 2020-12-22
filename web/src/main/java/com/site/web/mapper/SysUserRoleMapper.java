package com.site.web.mapper;

import com.site.web.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Describe: 用户角色接口
 * 这个是单表操作，可以用mybatis-plus
 * */
@Mapper
public interface SysUserRoleMapper {

    int batchInsert(List<SysUserRole> sysUserRoles);

    int deleteByUserId(String userId);

    int deleteByUserIds(String[] userIds);

    int deleteByRoleId(String roleId);

    int deleteByRoleIds(String[] roleIds);

    List<SysUserRole> selectByUserId(String userId);
}
