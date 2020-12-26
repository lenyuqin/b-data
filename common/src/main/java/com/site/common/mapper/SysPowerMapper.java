package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.SysPower;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysPower)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-23 19:57:24
 */
@Mapper
@Repository
public interface SysPowerMapper extends BaseMapper<SysPower> {


}