package com.advance.scaffold.core.model;

import java.util.Collections;
import java.util.List;

public class Grid implements java.io.Serializable {

	private Integer total;
	private List rows = Collections.EMPTY_LIST;
	private List footer = Collections.EMPTY_LIST;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
