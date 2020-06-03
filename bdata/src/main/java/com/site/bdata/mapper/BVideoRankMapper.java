package com.site.bdata.mapper;

import com.site.bdata.entity.BVideoRank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (BVideoRank)表数据库访问层
 *
 * @author lmk
 * @since 2020-06-03 10:20:01
 */
@Mapper
@Repository 
public interface BVideoRankMapper extends BaseMapper<BVideoRank> {


}