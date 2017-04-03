package com.shuanglong.utils;

import java.awt.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ColorUtil
{
	private static Logger log = LogManager.getLogger(ColorUtil.class);
	private static ColorUtil mInstance = new ColorUtil();

	private ColorUtil()
	{
	}

	public static void TestEntry()
	{
        System.out.println( "---------------------------------- ColorUtil Test ----------------------------------" );
		Color testColor = mInstance.getColor(0x00ff0000);
		log.debug("testColor=" + testColor.toString());
		Color testAlphaColor = mInstance.getColor(0x00ff0000, true);
		log.debug("testAlphaColor=" + testAlphaColor.toString());
	}

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
	WHITE(0x00FFFFFF), BLACK(0x00000000), RED(0x00FF0000), GREEN(0x0000FF00), BLUE(0x000000FF);
	
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
