package com.site.web.controller;

import com.site.common.entity.BMenu;
import com.site.common.entity.SysUser;
import com.site.common.service.BMenuService;
import com.site.common.service.SysUserService;
import com.site.common.web.base.BaseController;
import com.site.component.utils.security.SecurityUtil;
import com.site.web.entity.SysMenu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenyuqin
 * @Describe 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("system/user")
@Api(value = "用户controller", tags = {"用户操作接口"})
public class SysUserController extends BaseController {


    @Resource
    private BMenuService bMenuService;

    @Resource
    private SysUserService sysUserService;

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
        List<BMenu> bMenuList = bMenuService.list();
        //将BMenu转化为SysMenu
        List<SysMenu> sysMenuList = bMenuList.stream().map(bMenu -> {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(bMenu.getId());
            sysMenu.setParentId(bMenu.getParentid());
            sysMenu.setTitle(bMenu.getTitle());
            sysMenu.setType(bMenu.getType());
            sysMenu.setOpenType(bMenu.getOpentype());
            sysMenu.setIcon(bMenu.getIcon());
            sysMenu.setHref(bMenu.getHref());
            return sysMenu;
        }).collect(Collectors.toList());
        List<SysMenu> sysMenus = toUserMenu(sysMenuList, "0");
        return sysMenus;
    }


    /**
     * Describe: 跳转用户个人资料
     * Param: null
     * Return: ModelAndView
     */
    @GetMapping("center")
    @ApiOperation(value = "个人资料")
    public ModelAndView center(Model model) {
        SysUser sysUser = (SysUser) SecurityUtil.currentUser().getPrincipal();
        model.addAttribute("userInfo", sysUserService.getById(sysUser.getUserId()));
        return JumpPage(MODULE_PATH + "center");
    }


    /**
     * 递归获取菜单tree
     *
     * @param sysMenus
     * @param parentId
     * @return
     */
    public List<SysMenu> toUserMenu(List<SysMenu> sysMenus, String parentId) {
        List<SysMenu> list = new ArrayList<>();
        for (SysMenu menu : sysMenus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(toUserMenu(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }


}
