package com.site.bdata.controller;

import com.site.bdata.entity.BVideoData;
import com.site.bdata.service.BVideoDataService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (BVideoData)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:19:24
 */
@Api(tags = "(BVideoData)") 
@RestController
@RequestMapping("bVideoData")
public class BVideoDataController {
    /**
     * 服务对象
     */
    @Resource
    private BVideoDataService bVideoDataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据id查询 ")
    @GetMapping("selectOne/{id}")
    public BVideoData selectOne(@ApiParam(value = " ID") @PathVariable("id")  id) {
        return this.bVideoDataService.queryById(id);
    }

}