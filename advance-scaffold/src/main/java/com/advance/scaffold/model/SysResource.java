package com.advance.scaffold.model;

import com.advance.scaffold.core.model.AutoBaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 *
 * 资源
 *
 */
@TableName("sys_resource")
public class SysResource extends AutoBaseModel {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	private String name;

	private String url;

	private String description;

	private String icon;

	private Integer pid;

	@TableField(value = "state")
	private Integer rstate;

	@TableField(value = "resource_type")
	private Integer resourceType;

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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getRstate() {
		return rstate;
	}

	public void setRstate(Integer rstate) {
		this.rstate = rstate;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
