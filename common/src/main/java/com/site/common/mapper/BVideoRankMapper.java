package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BVideoRank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BVideoRank)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-21 15:53:48
 */
@Mapper
@Repository
public interface BVideoRankMapper extends BaseMapper<BVideoRank> {


}