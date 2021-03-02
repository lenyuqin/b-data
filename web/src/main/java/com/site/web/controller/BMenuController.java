package com.site.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.common.entity.BMenu;
import com.site.common.service.BMenuService;

import com.site.common.web.base.BaseController;
import com.site.common.web.response.ResuTree;
import com.site.common.web.response.Result;
import com.site.common.web.response.ResultTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author lenyuqin
 */
@Slf4j
@RestController
@RequestMapping("system/menu")
public class BMenuController extends BaseController {


    @Resource
    private BMenuService bMenuService;

    /**
     * Describe: 基础路径
     */
    private static final String MODULE_PATH = "system/menu/";


    /**
     * Describe: 获取权限列表视图
     * Param ModelAndView
     * Return 权限列表视图
     */
    @GetMapping("main")
    public ModelAndView main() {
        return JumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取权限列表数据
     * Param ModelAndView
     * Return 权限列表数据
     */
    @GetMapping("data")
    public ResultTable data() {
        return treeTable(bMenuService.list());

    }

    /**
     * Describe: 获取权限新增视图
     * Param ModelAndView
     * Return 权限新增视图
     */
    @GetMapping("add")
    public ModelAndView add() {
        return JumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 获取权限修改视图
     * Param ModelAndView
     * Return 权限修改视图
     */
    @GetMapping("edit")
    public ModelAndView edit(Model model, @RequestParam(value = "id") String id) {
        log.info(id);
        model.addAttribute("bMenu", bMenuService.getById(id));
        return JumpPage(MODULE_PATH + "edit");
    }


    /**
     * Describe: 根据 id 进行删除
     *
     * @param id 菜单节点id
     * @return ResuTree
     */
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        List<BMenu> list = bMenuService.list(new QueryWrapper<BMenu>().lambda().eq(BMenu::getParentid, id));
        boolean result;
        //子节点
        if (list.size() == 0) {
            result = bMenuService.removeById(id);
        } else {//父节点
            List<String> idList = list.stream().map(BMenu::getId).collect(Collectors.toList());
            result = bMenuService.removeByIds(idList);
            bMenuService.removeById(id);
        }
        return decide(result);
    }


    /**
     * Describe: 保存权限信息
     * Param: SysPower
     * Return: ResuBean
     */
    @PostMapping("save")
    public Result save(@RequestBody BMenu bMenu) {
        boolean result = bMenuService.save(bMenu);
        return decide(result);
    }

    /**
     * Describe: 修改权限信息
     * Param SysPower
     * Return 执行结果
     */
    @PutMapping("update")
    public Result update(@RequestBody BMenu bMenu) {
        boolean result = bMenuService.updateById(bMenu);
        return decide(result);
    }


    /**
     * Describe: 获取父级权限选择数据
     * Param sysPower
     * Return ResuTree
     */
    @GetMapping("selectParent")
    public ResuTree selectParent(BMenu bMenu) {
        List<BMenu> list;
        if (bMenu.getParentid() != null && "".equals(bMenu.getParentid())) {
            list = bMenuService.list(new QueryWrapper<BMenu>().lambda().eq(BMenu::getParentid, bMenu.getParentid()));
        } else {
            list = bMenuService.list();
        }
        BMenu basePower = new BMenu();
        basePower.setTitle("总菜单");
        basePower.setParentid("-1");
        basePower.setId("0");
        list.add(basePower);
        return dataTree(list);
    }


}
