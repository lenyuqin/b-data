package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysRole)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-23 19:57:41
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {


}