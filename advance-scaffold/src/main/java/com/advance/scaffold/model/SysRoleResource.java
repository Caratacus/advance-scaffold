package com.advance.scaffold.model;

import com.advance.scaffold.core.model.AutoBaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 角色资源
 *
 */
@TableName("sys_role_resource")
public class SysRoleResource extends AutoBaseModel {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableField(value = "role_id")
	private Long roleId;

	@TableField(value = "resource_id")
	private Long resourceId;

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
