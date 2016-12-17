package com.advance.scaffold.mapper;

import com.advance.scaffold.model.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 *
 * SysRole 表数据库控制层接口
 *
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 获取该角色详细信息
	 * 
	 * @param id
	 * @return
	 */
	public SysRole getBySelect(Long id);

}