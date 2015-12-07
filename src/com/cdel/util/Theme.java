package com.cdel.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Theme implements Serializable {
	private String name;
	private String displayName;
	private String image;

	public Theme() {

	}

	public Theme(String name, String displayName, String image) {
		this.name = name;
		this.displayName = displayName;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return name;
	}

}
