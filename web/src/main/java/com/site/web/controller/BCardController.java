package com.site.web.controller;


import com.site.common.service.BVideoDataService;
import com.site.common.service.BVideoHistoryService;
import com.site.common.service.BVideoRankService;
import com.site.common.web.base.BaseController;
import com.site.common.web.response.ResultTable;
import com.site.web.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author lenyuqin
 * @data 2021/1/1
 */

@Slf4j
@RestController
@RequestMapping("system/card")
public class BCardController extends BaseController {

    @Resource
    private BVideoHistoryService bVideoHistoryService;
    @Resource
    private BVideoDataService bVideoDataService;
    @Resource
    private BVideoRankService service;
    @Resource
    private CardService cardService;


    /**
     * Describe: 基础路径
     */
    private static final String MODULE_PATH = "system/card/";

    /**
     * Describe: 获取权限列表视图
     * Param ModelAndView
     * Return 权限列表视图
     */
    @GetMapping("card")
    public ModelAndView main() {
        return JumpPage(MODULE_PATH + "card");
    }


    /**
     * Describe: 获取权限列表数据
     * Param ModelAndView
     * Return 权限列表数据
     */
    @GetMapping("data")
    public ResultTable data() {
        return treeTable(cardService.getPagesVideo());
    }


}
