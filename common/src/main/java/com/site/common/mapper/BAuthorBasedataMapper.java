package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BAuthorBasedata;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BAuthorBasedata)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-21 15:52:43
 */
@Mapper
@Repository
public interface BAuthorBasedataMapper extends BaseMapper<BAuthorBasedata> {


}