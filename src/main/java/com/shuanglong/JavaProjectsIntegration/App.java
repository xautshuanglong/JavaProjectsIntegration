package com.shuanglong.JavaProjectsIntegration;

import com.shuanglong.utils.NumberUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shuanglong.utils.ColorUtil;

public class App 
{
	private static Logger log = LogManager.getLogger(Number.class);
	
	public static void main( String[] args )
	{
		log.info("==================== Maven Test");
		
		NumberUtil.TestEntry();
		ColorUtil.TestEntry();
	}
}
