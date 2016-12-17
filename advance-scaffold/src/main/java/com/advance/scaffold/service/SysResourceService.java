package com.advance.scaffold.service;

import java.util.List;

import com.advance.scaffold.core.model.SessionInfo;
import com.baomidou.mybatisplus.service.IService;
import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.model.SysResource;

/**
 *
 * SysResource 表数据服务层接口
 *
 */
public interface SysResourceService extends IService<SysResource> {

	public List<Tree> tree(SessionInfo sessionInfo);

	public List<Tree> listAllTree(boolean flag);

	public List<SysResource> treeGrid();

	/**
	 * 查询所有资源列表
	 * 
	 * @return
	 */
	public List<String> listAllResource();
}