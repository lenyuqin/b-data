package com.site.bdata.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.bdata.entity.BVideoRank;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoRankService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (BVideoRank)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:20:01
 */
@Api(tags = "(BVideoRank)") 
@RestController
@RequestMapping("bVideoRank")
public class BVideoRankController {
    /**
     * 服务对象
     */
    @Resource
    private BVideoDataService bVideoDataService;
    @Resource
    private BVideoRankService bVideoRankService;



    /**
     * 查询排行榜的记录
     * */
    @GetMapping({"/page/{zone}/{pageNum}"})
    public String getBVideoDataController(HttpServletRequest request, @PathVariable("pageNum") int pageNum, @PathVariable("zone") int zone) {
        Page<BVideoRank> page = new Page<>(pageNum, 10);
        List<BVideoRank> bVideoRanks = bVideoRankService.page(page, new QueryWrapper<BVideoRank>()
                .lambda().eq(BVideoRank::getBvRankzone, zone)
                .orderByAsc(BVideoRank::getBvTime)).getRecords();
        return bVideoRanks.toString();
    }


}