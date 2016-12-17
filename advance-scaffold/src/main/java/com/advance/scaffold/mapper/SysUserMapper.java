package com.advance.scaffold.mapper;

import java.util.List;
import java.util.Map;

import com.advance.scaffold.model.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * SysUser 表数据库控制层接口
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 根据用户id查询用户资源列表
	 *
	 * @param id
	 * @return
	 */
	public List<String> listResource(Long id);

	/**
	 * 获取用户列表
	 *
	 * @param page
	 * @param map
	 * @return
	 */
	public List<SysUser> getUsers(Page page, Map map);

	/**
	 * 获取用户列数
	 *
	 * @param map
	 * @return
	 */
	public int getUsersCount(Map map);

	/**
	 * 获取某一用户的详细信息，包括角色id列表
	 * 
	 * @param id
	 * @return
	 */
	public SysUser getInfo(Long id);

}