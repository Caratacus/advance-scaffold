package com.advance.scaffold.service.impl;

import com.advance.scaffold.core.model.TreeLay;
import com.app.common.TypeConvert;
import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.model.SysRole;
import com.advance.scaffold.model.SysRoleResource;
import com.advance.scaffold.mapper.SysRoleMapper;
import com.advance.scaffold.service.SysRoleResourceService;
import com.advance.scaffold.service.SysRoleService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * SysRole 表数据服务层接口实现类
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRoleResourceService sysRoleResourceService;

	@Override
	public Page<SysRole> dataGrid(SysRole role, Page page) {
		Page<SysRole> sysRolePage = this.selectPage(page, Condition.instance().like("name", role.getName()));
		return sysRolePage;
	}

	@Override
	public List<Tree> tree() {
		List<Tree> trees = new ArrayList<Tree>();
		List<SysRole> sysRoles = this.selectList(Condition.Empty());
		if ((sysRoles != null) && (sysRoles.size() > 0)) {
			for (SysRole sysRole : sysRoles) {
				Tree tree = new Tree();
				tree.setId(sysRole.getId().toString());
				tree.setText(sysRole.getName());
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<TreeLay> treeLay() {
		List<TreeLay> trees = new ArrayList<TreeLay>();
		List<SysRole> sysRoles = this.selectList(Condition.Empty());
		if ((sysRoles != null) && (sysRoles.size() > 0)) {
			for (SysRole sysRole : sysRoles) {
				TreeLay tree = new TreeLay();
				tree.setId(sysRole.getId().toString());
				tree.setName(sysRole.getName());
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public void grant(SysRole role) {
		// 清空该角色的所有权限
		SysRoleResource roleResource = new SysRoleResource();
		roleResource.setRoleId(role.getId());
		sysRoleResourceService.delete(new EntityWrapper<SysRoleResource>(roleResource));
		// 添加该角色的所有权限
		if ((role.getResourceIds() != null) && !role.getResourceIds().equalsIgnoreCase("")) {
			List<SysRoleResource> sysRoleResources = new ArrayList<SysRoleResource>();
			if (role.getResourceIds().contains(",")) {
				for (String id : role.getResourceIds().split(",")) {
					SysRoleResource sysRoleResource = new SysRoleResource();
					sysRoleResource.setRoleId(role.getId());
					sysRoleResource.setResourceId(TypeConvert.toLong(id));
					sysRoleResources.add(sysRoleResource);
				}
			} else {
				SysRoleResource sysRoleResource = new SysRoleResource();
				sysRoleResource.setRoleId(role.getId());
				sysRoleResource.setResourceId(TypeConvert.toLong(role.getResourceIds()));
				sysRoleResources.add(sysRoleResource);
			}
			sysRoleResourceService.insertBatch(sysRoleResources);
		}
	}

	@Override
	public SysRole getBySelect(Long id) {
		return sysRoleMapper.getBySelect(id);
	}

}