package com.advance.scaffold.core.model;

import java.util.List;

public class TreeLay implements java.io.Serializable {

	private String id;
	private String name;
//	private String state = "open";// open,closed
	private boolean checked = true;
//	private Object attributes;
	private List<TreeLay> children;
//	private String iconCls;
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeLay> getChildren() {
		return children;
	}

	public void setChildren(List<TreeLay> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
