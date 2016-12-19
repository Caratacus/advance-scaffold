package com.advance.scaffold.service.impl;

import com.advance.scaffold.core.model.SessionInfo;
import com.advance.scaffold.mapper.SysResourceMapper;
import com.advance.scaffold.model.SysResource;
import com.advance.scaffold.service.SysResourceService;
import com.advance.scaffold.core.model.Tree;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * SysResource 表数据服务层接口实现类
 *
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public List<Tree> tree(SessionInfo sessionInfo) {
		List<SysResource> sysResources = null;
		List<Tree> trees = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourcetype", 0);// 菜单类型的资源

		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 自查自己有权限的资源
			sysResources = sysResourceMapper.getResourceList(params);
		} else {
			return null;
		}

		if ((sysResources != null) && (sysResources.size() > 0)) {
			for (SysResource sysResource : sysResources) {
				Tree tree = new Tree();
				tree.setId(sysResource.getId().toString());
				if (sysResource.getPid() != null) {
					tree.setPid(sysResource.getPid().toString());
				} else {
					tree.setState("closed");
				}
				tree.setText(sysResource.getName());
				tree.setIconCls(sysResource.getIcon());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("url", sysResource.getUrl());
				tree.setAttributes(map);
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<Tree> listAllTree(boolean flag) {

		List<SysResource> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		if (flag) {
			l = sysResourceMapper.getAllResources();
		} else {
			l = sysResourceMapper.getResourceAllTree2();
		}
		if ((l != null) && (l.size() > 0)) {
			for (SysResource r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				if (r.getPid() != null) {
					tree.setPid(r.getPid().toString());
				}/*
				 * else { tree.setState("closed"); }
				 */
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<SysResource> treeGrid() {
		List<SysResource> l = sysResourceMapper.getResourceTree();
		return l;
	}

	@Override
	public List<String> listAllResource() {
		List<String> resourceList = new ArrayList<String>();
		List<SysResource> list = this.selectList(Condition.Empty());
		for (int i = 0; i < list.size(); i++) {
			resourceList.add(list.get(i).getUrl());
		}
		return resourceList;
	}
}