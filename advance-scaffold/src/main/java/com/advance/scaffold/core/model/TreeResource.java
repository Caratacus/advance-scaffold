package com.advance.scaffold.core.model;

import java.util.Date;
import java.util.List;

/**
 *
 * 资源
 *
 */
public class TreeResource extends AutoBaseModel {

	private String name;

	private String url;

	private String description;

	private String icon;

	private Integer pid;

	private Integer state;

	private Integer resourceType;

	private Date createTime;

	private List<TreeResource> childrens;

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public List<TreeResource> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<TreeResource> childrens) {
		this.childrens = childrens;
	}
}
