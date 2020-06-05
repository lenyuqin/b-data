package com.site.bdata.controller;

import com.site.bdata.service.BVideoDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;

/**
 * (BVideoData)表控制层
 *
 * @author lmk
 * @since 2020-06-03 10:19:24
 */
@Api(tags = "(BVideoData)表控制层")
@RestController
@RequestMapping("bVideoData")
public class BVideoDataController {
    /**
     * 服务对象
     */
    @Resource
    private BVideoDataService bVideoDataService;


    @GetMapping("/hello")
    public String getBVideoDataController() {
        return "hello world";
    }



}