package com.shuanglong.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumberUtilTest
{
	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("NumberUtilTest.beforeClass");
	}
	
	@Before
	public void beforeTestApp()
	{
		System.out.println("NumberUtilTest.beforeTestApp");
	}
	
	@Test
	public void testAdd()
	{
		Assert.assertEquals(4, 1+3);
		System.out.println("NumberUtilTest.testAdd");
	}
	
	@Test
	public void testMinus()
	{
		Assert.assertEquals(3, 4-1);
		System.out.println("NumberUtilTest.testMinus");
	}
	
	@After
	public void afterTestApp()
	{
		System.out.println("NumberUtilTest.afterTestApp");
	}
	
	@AfterClass
	public static void afterClass()
	{
		System.out.println("NumberUtilTest.afterClass");
	}
}
