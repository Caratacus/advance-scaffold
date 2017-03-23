package com.advance.scaffold.core.model;

import java.util.List;

public class UserSessionInfo implements java.io.Serializable {

	private Long id;// 用户ID
	private String loginName;// 登录名
	private String name;// 姓名
	private Integer sex;// 性别
	private String ip;// 用户IP

	private List<String> resourceList;// 用户可以访问的资源地址列表

	private List<String> resourceAllList;

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getResourceAllList() {
		return resourceAllList;
	}

	public void setResourceAllList(List<String> resourceAllList) {
		this.resourceAllList = resourceAllList;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
