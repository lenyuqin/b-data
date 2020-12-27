package com.site.web.controller;

import com.site.web.entity.SysMenu;
import com.site.web.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lenyuqin
 * @Describe 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("system/user")
@Api(value = "用户controller", tags = {"用户操作接口"})
public class SysUserController extends BaseController {

    /**
     * Describe: 基础路径
     */
    private static final String MODULE_PATH = "system/user/";


    /**
     * Describe: 获取用户列表视图
     * Param ModelAndView
     * Return 用户列表视图
     */
    @GetMapping("main")
    @ApiOperation(value = "获取用户列表视图")
    public ModelAndView main() {
        return JumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取用户列表数据
     * Param ModelAndView
     * todo 这个是分页 需要进行修改
     * Return 用户列表数据
     */

    @GetMapping("getUserMenu")
    public List<SysMenu> getUserMenu() {
        ArrayList<SysMenu> sysMenulist1 = new ArrayList<>();
        ArrayList<SysMenu> sysMenulist2 = new ArrayList<>();

        SysMenu sysMenuParent = new SysMenu();
        sysMenuParent.setId("1");
        sysMenuParent.setParentId("0");
        sysMenuParent.setTitle("test");
        sysMenuParent.setType("1");
        sysMenuParent.setOpenType("_iframe");
        sysMenuParent.setIcon( "layui-icon layui-icon layui-icon layui-icon layui-icon-rate");
        sysMenuParent.setHref("");
        sysMenuParent.setUsername(null);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId("2");
        sysMenu.setParentId("1");
        sysMenu.setTitle("系统管理");
        sysMenu.setType("0");
        sysMenu.setOpenType(null);
        sysMenu.setIcon("layui-icon layui-icon-vercode");
        sysMenu.setHref("/system/user/main");
        sysMenu.setChildren(null);
        sysMenu.setUsername(null);
        sysMenulist1.add(sysMenu);
        sysMenuParent.setChildren(sysMenulist1);
        sysMenulist2.add(sysMenuParent);
        return sysMenulist2;
    }


}
