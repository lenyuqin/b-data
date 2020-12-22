package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BVideoData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BVideoData)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-21 15:53:09
 */
@Mapper
@Repository
public interface BVideoDataMapper extends BaseMapper<BVideoData> {


}