package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BOnline;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BOnline)表数据库访问层
 *
 * @author lenyuqin
 * @since 2021-01-31 15:40:39
 */
@Mapper
@Repository
public interface BOnlineMapper extends BaseMapper<BOnline> {


}