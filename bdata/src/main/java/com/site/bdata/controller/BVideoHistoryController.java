package com.site.bdata.controller;

import com.site.bdata.entity.BVideoHistory;
import com.site.bdata.service.BVideoHistoryService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

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



}