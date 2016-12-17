package com.advance.scaffold.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.model.SysOrganization;

/**
 *
 * SysOrganization 表数据服务层接口
 *
 */
public interface SysOrganizationService extends IService<SysOrganization> {
	/**
	 * 组织列表
	 * 
	 * @return
	 */
	public List<SysOrganization> treeGrid();

	public List<Tree> tree();

}