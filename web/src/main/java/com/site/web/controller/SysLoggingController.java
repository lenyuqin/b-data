package com.site.web.controller;




import com.site.common.web.base.BaseController;
import com.site.log.service.LoggingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Describe 日 志 控 制 器
 * @author lenyuqin
 */
@RestController
@RequestMapping("system/logging")
public class SysLoggingController extends BaseController {


    /**
     * 引 入 日 志 组 件  的 日 志 服 务
     */
    @Resource
    private LoggingService loggingService;

    /**
     * Describe: 行为日志视图
     * Param: null
     * Return: ModelAndView
     */
    @GetMapping("main")
    public ModelAndView main() {
        return JumpPage("system/logging/main");
    }

    /**
     * Describe: 操作日志数据
     * Param: null
     * Return: ResultTable
     */
    //@GetMapping("operateLog")
    //@PreAuthorize("hasPermission('/system/logging/operateLog','sys:logging:operateLog')")
    //public ResultTable operateLog(PageDomain pageDomain) {
    //    PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
    //    PageInfo<Logging> pageInfo = new PageInfo<>(loggingService.data(LoggingType.OPERATE));
    //    return pageTable(pageInfo.getList(), pageInfo.getTotal());
    //}

    /**
     * Describe: 登录日志数据
     * Param: null
     * Return: ModelAndView
     */
    //@GetMapping("loginLog")
    //@PreAuthorize("hasPermission('/system/logging/loginLog','sys:logging:loginLog')")
    //public ResultTable loginLog(PageDomain pageDomain) {
    //    PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
    //    PageInfo<Logging> pageInfo = new PageInfo<>(loggingService.data(LoggingType.LOGIN));
    //    return pageTable(pageInfo.getList(), pageInfo.getTotal());
    //}
    @GetMapping("/details")
    public ModelAndView details() {
        return JumpPage("system/logging/details");
    }
}
