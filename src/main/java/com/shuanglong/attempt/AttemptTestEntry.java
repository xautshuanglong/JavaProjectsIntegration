package com.shuanglong.attempt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shuanglong.utils.UtilTestEntry;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;

public class AttemptTestEntry
{
	private static Logger mLogger = LogManager.getLogger(UtilTestEntry.class.getName());
	
	private volatile static AttemptTestEntry singleton = new AttemptTestEntry();
	
	private AttemptTestEntry()
	{
		mLogger.info("----> AttemptTestEntry.UtilTestEntry() <----");
	}
	
	public void Enter()
	{
		mLogger.info("begin test case ...");
	}
	
	public void Exit()
	{
		;
	}
	
	public static AttemptTestEntry getInstance()
	{
		InstancHelpper t1 = new InstancHelpper(1);
		InstancHelpper t2 = new InstancHelpper(2);
		return InstancHelpper.singleInstance;
	}
	
	private static class InstancHelpper
	{
		private static AttemptTestEntry singleInstance = new AttemptTestEntry();
		
		public InstancHelpper(int id)
		{
			mLogger.info("enter constructor id="+id);
		}
	}
}
