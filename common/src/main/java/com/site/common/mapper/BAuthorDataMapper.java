package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BAuthorData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BAuthorData)表数据库访问层
 *
 * @author lenyuqin
 * @since 2021-01-03 13:05:22
 */
@Mapper
@Repository
public interface BAuthorDataMapper extends BaseMapper<BAuthorData> {


}