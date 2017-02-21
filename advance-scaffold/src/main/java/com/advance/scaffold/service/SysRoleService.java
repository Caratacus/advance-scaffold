package com.advance.scaffold.service;

import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.core.model.TreeLay;
import com.advance.scaffold.model.SysRole;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *
 * SysRole 表数据服务层接口
 *
 */
public interface SysRoleService extends IService<SysRole> {

    public Page<SysRole> dataGrid(SysRole role, Page page);

    public List<Tree> tree();

    public List<TreeLay> treeLay();

    public void grant(SysRole role);

    public SysRole getBySelect(Long id);

}