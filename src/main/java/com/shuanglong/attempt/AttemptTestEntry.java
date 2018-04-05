package com.shuanglong.attempt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shuanglong.utils.UtilTestEntry;

public class AttemptTestEntry
{
	private static Logger mLogger = LogManager.getLogger(UtilTestEntry.class.getName());
	private static AttemptTestEntry singleton = null;
	
	private AttemptTestEntry()
	{
	}
	
	public void Enter()
	{
		mLogger.info("Begin test case ...");
	}
	
	public void Exit()
	{
		;
	}
	
	public static AttemptTestEntry getInstance()
	{
		if (singleton == null)
		{
			synchronized (AttemptTestEntry.class)
			{
				if (singleton == null)
				{
					singleton = new AttemptTestEntry();
				}
			}
		}
		return singleton;
	}
}
