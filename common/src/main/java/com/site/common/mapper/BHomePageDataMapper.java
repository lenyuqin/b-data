package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BHomePageData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BHomePageData)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-22 11:55:20
 */
@Mapper
@Repository
public interface BHomePageDataMapper extends BaseMapper<BHomePageData> {


}