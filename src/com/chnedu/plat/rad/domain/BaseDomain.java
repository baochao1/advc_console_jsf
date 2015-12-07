package com.chnedu.plat.rad.domain;

public class BaseDomain {

	/**
	 * 取数据起始行，用于分页
	 */
	int rowNumStart = 0;

	/**
	 * 取数据结束行，用于分页
	 */
	int rowNumEnd = 0;

	/**
	 * 排序字段
	 */
	String sortField;
	/**
	 * 排序字段顺序
	 */
	String sortOrder;
	/**
	 * 网站id
	 * 
	 */
	public static Integer baseSiteID;
	
	public Integer getBaseSiteID() {
		if(baseSiteID == null){
			return 1;
		}else {
			return baseSiteID;
		}
	}

	public int getRowNumStart() {
		return rowNumStart;
	}

	public void setRowNumStart(int rowNumStart) {
		this.rowNumStart = rowNumStart;
	}

	public int getRowNumEnd() {
		return rowNumEnd;
	}

	public void setRowNumEnd(int rowNumEnd) {
		this.rowNumEnd = rowNumEnd;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
