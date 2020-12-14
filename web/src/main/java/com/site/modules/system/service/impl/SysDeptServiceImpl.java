package com.site.modules.system.service.impl;


import com.site.modules.system.domain.SysDept;
import com.site.modules.system.mapper.SysDeptMapper;
import com.site.modules.system.mapper.SysUserMapper;
import com.site.modules.system.service.ISysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDeptServiceImpl implements ISysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * Describe: 查询部门数据
     * Param: QueryRoleParam
     * Return: 操作结果
     * */
    @Override
    public List<SysDept> list(SysDept param) {
        return sysDeptMapper.selectList(param);
    }

    /**
     * Describe: 查询部门数据 分页
     * Param: QueryRoleParam
     * Return: 操作结果
     * */
    //@Override
    //public PageInfo<SysDept> page(SysDept param, PageDomain pageDomain) {
    //    PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
    //    List<SysDept> list = sysDeptMapper.selectList(param);
    //    return new PageInfo<>(list);
    //}

    /**
     * Describe: 保存部门数据
     * Param: SysDept
     * Return: 操作结果
     * */
    @Override
    public boolean save(SysDept sysDept) {
        int result = sysDeptMapper.insert(sysDept);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 查询部门
     * Param: id
     * Return: 返回部门信息
     * */
    @Override
    public SysDept getById(String id) {
        return sysDeptMapper.selectById(id);
    }

    /**
     * Describe: 修改用户数据
     * Param: SysUser
     * Return: 操作结果
     * */
    @Override
    public boolean update(SysDept sysDept) {
        Integer result = sysDeptMapper.updateById(sysDept);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 id 删除部门数据
     * Param: id
     * Return: Boolean
     * */
    @Override
    @Transactional
    public Boolean remove(String id) {
        int deptResult = sysDeptMapper.deleteById(id);
        int deptUserResult = sysUserMapper.resetDeptByDeptId(id);
        if(deptResult>0 && deptUserResult>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 id 批量删除部门数据
     * Param: ids
     * Return: Boolean
     * */
    @Override
    public boolean batchRemove(String[] ids) {
        int deptResult = sysDeptMapper.deleteByIds(ids);
        int deptUserResult = sysUserMapper.resetDeptByDeptIds(ids);
        if(deptResult>0 && deptUserResult>0){
            return true;
        }else{
            return false;
        }
    }
}
