package com.advance.scaffold.service;

import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.core.model.TreeResource;
import com.advance.scaffold.model.SysResource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *
 * SysResource 表数据服务层接口
 *
 */
public interface SysResourceService extends IService<SysResource> {

	/**
	 * 根据用户ID获取树行列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<TreeResource> treeResources(Long userId);

	public List<Tree> listAllTree(boolean flag);

	public List<SysResource> treeGrid();

	/**
	 * 查询所有资源列表
	 * 
	 * @return
	 */
	public List<String> listAllResource();
}