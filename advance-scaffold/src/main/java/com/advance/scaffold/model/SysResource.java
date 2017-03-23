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

	private String name;

	private String url;

	private String description;

	private String icon;

	private Integer pid;

	@TableField(value = "state")
	private Integer state;

	@TableField(value = "resource_type")
	private Integer resourceType;

	@TableField(value = "create_time")
	private Date createTime;

	@TableField(value = "create_time")
	private Integer seq;

	@TableField(exist = false)
	private String resourceTypeStr;

	@TableField(exist = false)
	private String stateStr;

	public SysResource() {
	}

	public SysResource(Long id, String name, String url, String description, String icon, Integer pid, Integer state,
					   Integer resourceType, String resourceTypeStr, Date createTime,String stateStr,Integer seq) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.description = description;
		this.icon = icon;
		this.pid = pid;
		this.state = state;
		this.resourceType = resourceType;
		this.createTime = createTime;
		this.resourceTypeStr = resourceTypeStr;
		this.stateStr = stateStr;
		this.seq = seq;
	}

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

	public String getResourceTypeStr() {
		return resourceTypeStr;
	}

	public void setResourceTypeStr(String resourceTypeStr) {
		this.resourceTypeStr = resourceTypeStr;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "SysResource{" + "id=" + id + ", name=" + name + ", url=" + url + ", description=" + description + ", icon="
				+ icon + ", pid=" + pid + ", state=" + state + ",resourceType=" + resourceType + ", createTime=" + createTime
				+ ", resourceTypeStr=" + resourceTypeStr + ",seq="+seq+" stateStr=" + stateStr + '}';
	}

}
