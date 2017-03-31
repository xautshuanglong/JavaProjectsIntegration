package com.shuanglong.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumberUtil
{
	private static Logger log = LogManager.getLogger(Number.class);
	
	public static void TestEntry()
	{
		float testFloat = 0.123456789f;
		
		byte[] floatByte = new byte[4];
		int tempInt = Float.floatToIntBits(testFloat);
		log.debug(String.format("tempInt = %d", tempInt));
		
		for(int i=0;i<4;i++)
		{
			floatByte[i] = (byte)((tempInt>>(i*8)) & 0xFF);
		}
		
		int testInt = 0;
		for(int i=0;i<4;i++)
		{
			testInt += (int)((floatByte[i] & 0xFF) << (i*8));
		}
		float resFloat = Float.intBitsToFloat(testInt);
		log.debug("resFloat = " + resFloat);
		printBytes(floatByte);
	}
	
	
	private static void printBytes(byte[] toPrint)
	{
		String tempString = "";
		for(int i=0;i<toPrint.length;i++)
		{
			tempString += String.format("%02X", toPrint[i]);
		}
		
		System.out.println(tempString);
	}
}
