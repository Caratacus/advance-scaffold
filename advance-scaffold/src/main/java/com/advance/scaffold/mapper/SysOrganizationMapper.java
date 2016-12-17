package com.advance.scaffold.mapper;

import java.util.List;

import com.advance.scaffold.model.SysOrganization;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 *
 * SysOrganization 表数据库控制层接口
 *
 */
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {

    /**
     * 获取部门列表
     *
     * @return
     */
    public List<SysOrganization> treeGrid();
}