package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BMenu)表数据库访问层
 *
 * @author lenyuqin
 * @since 2020-12-27 15:56:12
 */
@Mapper
@Repository
public interface BMenuMapper extends BaseMapper<BMenu> {


}