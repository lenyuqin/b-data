package com.site.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.common.entity.BCarousel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BCarousel)表数据库访问层
 *
 * @author lenyuqin
 * @since 2021-02-25 22:53:08
 */
@Mapper
@Repository
public interface BCarouselMapper extends BaseMapper<BCarousel> {


}