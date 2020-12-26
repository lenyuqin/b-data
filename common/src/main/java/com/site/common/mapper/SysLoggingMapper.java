package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.SysLogging;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysLogging)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-23 19:57:05
 */
@Mapper
@Repository
public interface SysLoggingMapper extends BaseMapper<SysLogging> {


}