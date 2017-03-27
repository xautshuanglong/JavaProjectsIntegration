package com.shuanglong.utils;

import java.awt.Color;

public class ColorUtil
{
	public Color getColor(int color)
	{
		return new Color(color);
	}
	
	public Color getColor(int color, boolean hasAlpha)
	{
		return new Color(color, hasAlpha);
	}
}

enum InnerColor
{
	WHITE(0x00FF0000), BLACK(0x00FF0000), RED(0x00FF0000), GREEN(0x00FF0000), BLUE(0x00FF0000);
	
	private int mColor;
	private InnerColor(int color)
	{
		mColor = color;
	}
	
	public int getColor()
	{
		return mColor;
	}
}
