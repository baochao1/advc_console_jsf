package com.cdel.util;

import java.awt.Color;
import java.io.Serializable;

public class PaintData implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer Width = 60;
	Integer Height = 20;
	Color background = new Color(0, 0, 0);
	Color drawColor = new Color(255, 255, 255);

	public PaintData() {
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getDrawColor() {
		return drawColor;
	}

	public void setDrawColor(Color drawColor) {
		this.drawColor = drawColor;
	}

	public Integer getHeight() {
		return Height;
	}

	public void setHeight(Integer height) {
		Height = height;
	}

	public Integer getWidth() {
		return Width;
	}

	public void setWidth(Integer width) {
		Width = width;
	}

}
