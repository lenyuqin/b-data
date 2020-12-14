package com.site.bdata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.bdata.entity.BAuthorBasedata;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BAuthorBasedata)表数据库访问层
 *
 * @author lmk
 * @since 2020-11-30 14:21:47
 */
@Mapper
@Repository
public interface BAuthorBasedataMapper extends BaseMapper<BAuthorBasedata> {


}