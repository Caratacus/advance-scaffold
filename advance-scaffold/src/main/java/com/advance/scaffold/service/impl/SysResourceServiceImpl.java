package com.advance.scaffold.service.impl;

import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.core.model.TreeResource;
import com.advance.scaffold.core.model.ZTree;
import com.advance.scaffold.mapper.SysResourceMapper;
import com.advance.scaffold.model.Department;
import com.advance.scaffold.model.SysResource;
import com.advance.scaffold.model.SysRole;
import com.advance.scaffold.service.SysResourceService;
import com.app.common.CollectionUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
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
	public List<TreeResource> treeResources(Long userId) {
		List<TreeResource> treeResources = sysResourceMapper.selectTreeResources(Condition.instance().eq("sr.state",0).eq("sur.user_id",userId).isNull("sr.pid"));
		if (CollectionUtils.isNotEmpty(treeResources)) {
			for (TreeResource treeResource : treeResources) {
				List<TreeResource> resources = sysResourceMapper.selectTreeResources(Condition.instance().eq("sr.state",0).eq("sur.user_id",userId).eq("sr.pid",treeResource.getId()));
				treeResource.setChildrens(resources);
			}
		}
		return treeResources;
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
				}
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
	public List<ZTree> listAllZTree(boolean flag) {

		List<SysResource> l = null;
		List<ZTree> lt = new ArrayList<ZTree>();
		if (flag) {
			l = sysResourceMapper.getAllResources();
		} else {
			l = sysResourceMapper.getResourceAllTree2();
		}
		if ((l != null) && (l.size() > 0)) {
			for (SysResource r : l) {
				ZTree tree = new ZTree();
				tree.setId(r.getId());
				if (r.getPid() != null) {
					tree.setPid(r.getPid());
				}

				tree.setName(r.getName());
//				Map<String, Object> attr = new HashMap<String, Object>();
//				attr.put("url", r.getUrl());
				tree.setFile(r.getUrl());
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public Page<SysResource> dataGrid(SysResource resource, Page page) {
		Page<SysResource> sysResourcePage = this.selectPage(page, Condition.instance().like("name", resource.getName()));
		return sysResourcePage;
	}

	@Override
	public List<SysResource> treeGrid() {
		List<SysResource> l = sysResourceMapper.getResourceTree();
		return l;
	}

	@Override
	public List<Department> treeGrid1() {
		List<Department> list = sysResourceMapper.getResourceTree1();
		return list;
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