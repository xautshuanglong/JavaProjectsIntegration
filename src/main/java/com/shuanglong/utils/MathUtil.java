package com.shuanglong.utils;

public class MathUtil
{
	public static int add(int value1, int value2)
	{
		return value1 + value2;
	}
	
	public static boolean isEvenNumber(int number)
	{
		if(number % 2 == 0)
		{
			return true;
		}
		
		return false;
	}
}
