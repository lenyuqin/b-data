package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BPopularData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BPopularData)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-23 13:11:24
 */
@Mapper
@Repository
public interface BPopularDataMapper extends BaseMapper<BPopularData> {


}