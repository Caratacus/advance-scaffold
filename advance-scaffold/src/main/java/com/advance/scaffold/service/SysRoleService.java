package com.advance.scaffold.service;

import java.util.List;

import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.core.model.TreeLay;
import com.advance.scaffold.model.SysRole;
import com.advance.scaffold.model.SysRoleResource;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 *
 * SysRole 表数据服务层接口
 *
 */
public interface SysRoleService extends IService<SysRole> {

    public Page<SysRole> dataGrid(SysRole role, Page page);

    public List<Tree> tree();

    public List<TreeLay> treeLay();

    public List<SysRoleResource> getRoleResources(SysRole role);

    public SysRole getBySelect(Long id);

}