package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysUser)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-23 19:58:13
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {


}