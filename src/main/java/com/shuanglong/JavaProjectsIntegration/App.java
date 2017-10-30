package com.shuanglong.JavaProjectsIntegration;

import com.shuanglong.attempt.AttemptTestEntry;
import com.shuanglong.utils.UtilTestEntry;

public class App 
{
	private static App appInstance = null;
	
	public static void main( String[] args )
	{
		appInstance = new App();
		
		appInstance.AppEntry();   // 应用入口：正式应用
		appInstance.TestEntry();  // 测试入口：测试专用
	}
	
	private void AppEntry()
	{
		;
	}
	
	private void TestEntry()
	{
		System.out.println("======================= App.TestEntry() =======================");
		UtilTestEntry.getInstance().Enter();
		UtilTestEntry.getInstance().Exit();
		
		AttemptTestEntry.getInstance().Enter();
		AttemptTestEntry.getInstance().Exit();
	}
}
