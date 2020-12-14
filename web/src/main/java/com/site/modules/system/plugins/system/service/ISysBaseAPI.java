package com.site.modules.system.plugins.system.service;

import com.site.modules.system.plugins.logging.enums.BusinessType;
import com.site.modules.system.plugins.logging.enums.LoggingType;
import com.site.modules.system.plugins.system.domain.*;
import java.util.List;


public interface ISysBaseAPI {

	/**
	 * 日志添加
	 * @param title 标题
	 * @param description 描述
	 * @param logType 日志类型(0:操作日志;1:登录日志;2:定时任务)
	 * @param operatetype 操作类型(1:添加;2:修改;3:删除;)
	 */
	void addLog(String title, String description, LoggingType logType, BusinessType operatetype);
	
	/**
	  * 根据用户账号查询用户信息
	 * @param username
	 * @return
	 */
	SysUserModel getUserByName(String username);


	/**
	  * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	SysUserModel getUserById(String id);
	
	/**
	 * 通过用户账号查询角色集合
	 * @param username
	 * @return
	 */
	List<SysRoleModel> getRolesByUsername(String username);






}
