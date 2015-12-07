package com.cdel.plat.grant.domain;

import java.io.Serializable;

public class MenuTab implements Serializable {
	private static final long serialVersionUID = 6467720460317210760L;
	private String tabID;
	private String tabName;
	private String src;
	private boolean rendered;

	public String getTabID() {
		return tabID;
	}

	public void setTabID(String tabID) {
		this.tabID = tabID;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

}
