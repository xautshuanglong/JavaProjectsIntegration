package com.shuanglong.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilTestEntry
{
	private static Logger mLogger = LogManager.getLogger(UtilTestEntry.class.getName());
	
	private UtilTestEntry()
	{
		mLogger.info("----> UtilTestEntry.UtilTestEntry() <----");
	}
	
	public static UtilTestEntry getInstance()
	{
		return InstancHelpper.singleInstance;
	}
	
	public void Enter()
	{
		mLogger.info("------------------------ UtilTestEntry.Enter() ------------------------");
		
		NumberUtil.TestEntry();
		ColorUtil.TestEntry();
	}
	
	public void Exit()
	{
		mLogger.info("------------------------ UtilTestEntry.Exit() ------------------------");
	}

	private static class InstancHelpper
	{
		private static UtilTestEntry singleInstance = new UtilTestEntry();
	}
}
