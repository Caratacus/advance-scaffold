package com.advance.scaffold.service.impl;

import com.app.common.TypeConvert;
import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.model.SysOrganization;
import com.advance.scaffold.mapper.SysOrganizationMapper;
import com.advance.scaffold.service.SysOrganizationService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * SysOrganization 表数据服务层接口实现类
 *
 */
@Service
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization> implements
		SysOrganizationService {
	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;

	@Override
	public List<SysOrganization> treeGrid() {
		List<SysOrganization> list = sysOrganizationMapper.treeGrid();
		return list;
	}

	@Override
	public List<Tree> tree() {
		List<SysOrganization> sysOrganizations = this.selectList(Condition.instance().orderBy("seq"));
		List<Tree> trees = new ArrayList<Tree>();
		if ((sysOrganizations != null) && (sysOrganizations.size() > 0)) {
			for (SysOrganization organization : sysOrganizations) {
				Tree tree = new Tree();
				tree.setId(TypeConvert.toString(organization.getId()));
				tree.setPid(TypeConvert.toString(organization.getPid()));
				tree.setText(organization.getName());
				tree.setIconCls(organization.getIcon());
				trees.add(tree);
			}
		}
		return trees;
	}
}