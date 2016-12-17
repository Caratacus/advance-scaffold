package com.advance.scaffold.model;

import java.util.Date;

import com.advance.scaffold.core.model.AutoBaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 组织机构
 *
 */
@TableName("sys_organization")
public class SysOrganization extends AutoBaseModel {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	private String name;

	private String address;

	private String code;

	private String icon;

	private Integer pid;

	private Integer seq;

	@TableField(value = "create_time")
	private Date createTime;

	@TableField(exist = false)
	private String iconCls;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createdatetime) {
		this.createTime = createdatetime;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
