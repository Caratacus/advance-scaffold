package com.advance.scaffold.service;

import java.util.List;

import com.advance.scaffold.core.model.SessionInfo;
import com.advance.scaffold.model.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * SysUser 表数据服务层接口
 *
 */
public interface SysUserService extends IService<SysUser> {
	/**
	 * 后台登陆
	 * 
	 * @param user
	 * @return
	 */
	public SysUser login(SysUser user);

	/**
	 * 获取用户列表
	 * 
	 * @param page
	 * @return
	 */
	Page<SysUser> getUsers(Page page);

	public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd);

	public List<String> listResource(Long id);

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	public void saveInfo(SysUser user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	public void updateInfo(SysUser user);

	public SysUser getInfo(Long id);

}