package com.site.bdata.controller;

import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.service.BVideoHistoryService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (BVideoHistory)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:19:48
 */
@Api(tags = "(BVideoHistory)") 
@RestController
@RequestMapping("bVideoHistory")
public class BVideoHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private BVideoHistoryService bVideoHistoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据id查询 ")
    @GetMapping("selectOne/{id}")
    public BVideoHistory selectOne(@ApiParam(value = "视频BV号 ID") @PathVariable("id") String id) {
        return this.bVideoHistoryService.queryById(id);
    }

}