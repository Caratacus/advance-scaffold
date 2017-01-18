package com.advance.scaffold.mapper;

import com.advance.scaffold.core.model.TreeResource;
import com.advance.scaffold.model.SysResource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysResource 表数据库控制层接口
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

	/**
	 * 获取当前用户的权限列表
	 *
	 * @param wrapper
	 * @return
	 */
	public List<TreeResource> selectTreeResources(@Param("ew") Wrapper wrapper);

	/**
	 * 获取当前用户的tree列表
	 *
	 * @return
	 */
	public List<SysResource> getResourceTree();

	/**
	 * 获取当前用户的所有tree列表--用于添加资源记录时显示所有资源tree
	 *
	 * @return
	 */
	public List<SysResource> getResourceAllTree2();

	/**
	 * 获取所有资源tree列表
	 *
	 * @param
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/12 0012
	 * @version 1.0
	 */
	public List<SysResource> getAllResources();
}