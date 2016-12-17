package com.advance.scaffold.model;

import com.advance.scaffold.core.model.AutoBaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 用户角色
 *
 */
@TableName("sys_user_role")
public class SysUserRole extends AutoBaseModel {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableField(value = "user_id")
	private Long userId;

	@TableField(value = "role_id")
	private Long roleId;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
