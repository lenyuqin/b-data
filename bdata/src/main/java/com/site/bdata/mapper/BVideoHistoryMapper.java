package com.site.bdata.mapper;

import com.site.bdata.entity.BVideoHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (BVideoHistory)表数据库访问层
 *
 * @author lmk
 * @since 2020-06-03 10:19:48
 */
@Mapper
@Repository 
public interface BVideoHistoryMapper extends BaseMapper<BVideoHistory> {


}