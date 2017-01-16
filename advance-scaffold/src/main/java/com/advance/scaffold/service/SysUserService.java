package com.advance.scaffold.service;

import com.advance.scaffold.core.model.UserSessionInfo;
import com.advance.scaffold.model.SysUser;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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

	public boolean editUserPwd(UserSessionInfo sessionInfo, String oldPwd, String pwd);

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