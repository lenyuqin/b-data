package com.site.bdata.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.site.bdata.entity.BVideoData;
import com.site.bdata.service.BVideoDataService;
import com.site.bdata.service.BVideoRankService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (BVideoData)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:19:24
 */
@Api(tags = "BVideoData表控制层")
@RestController
public class BVideoDataController {
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
    @GetMapping({"/video/{bvNumber}"})
    public String getBVideoDataController(HttpServletRequest request, @PathVariable("bvNumber") String bvNumber) {

        List<BVideoData> bVideoDataList = bVideoDataService
                .list(new QueryWrapper<BVideoData>().lambda().eq(BVideoData::getBvNumber, bvNumber));
//                .stream().filter(bVideoData -> {BVideoData::getBvTime}).collect(Collectors.toList());

        
        return bVideoDataList.toString();
    }





}