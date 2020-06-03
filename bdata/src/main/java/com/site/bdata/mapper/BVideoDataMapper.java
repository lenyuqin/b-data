package com.site.bdata.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (BVideoData)表数据库访问层
 *
 * @author lmk
 * @since 2020-06-03 10:19:22
 */
@Mapper
@Repository 
public interface BVideoDataMapper extends BaseMapper<BVideoData> {


}