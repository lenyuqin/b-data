package com.site.web.controller;

import com.site.component.utils.security.SecurityUtil;
import com.site.web.web.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Describe: 入 口 控 制 器
 * 这里也不需要怎么修改了，就是页面跳转
 * @author lenyuqin
 */
@RestController
@RequestMapping
public class SysEntranceController extends BaseController {

    /**
     * Describe: 获取登录视图
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("login")
    public ModelAndView login() {
        if (SecurityUtil.isAuthentication()) {
            return JumpPage("index");
        } else {
            return JumpPage("login");
        }
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("index")
    //@Logging(title = "主页", describe = "返回 Index 主页视图", type = BusinessType.ADD)
    public ModelAndView index() {
        return JumpPage("index");
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 主页视图
     */
    @GetMapping("console")
    public ModelAndView home() {
        return JumpPage("console/console");
    }

    /**
     * Describe:无权限页面
     * Return:返回403页面
     */
    @GetMapping("error/403")
    public ModelAndView noPermission() {
        return JumpPage("error/403");
    }

    /**
     * Describe:找不带页面
     * Return:返回404页面
     */
    @GetMapping("error/404")
    public ModelAndView notFound() {
        return JumpPage("error/404");
    }

    /**
     * Describe:异常处理页
     * Return:返回500界面
     */
    @GetMapping("error/500")
    public ModelAndView onException() {
        return JumpPage("error/500");
    }

}
