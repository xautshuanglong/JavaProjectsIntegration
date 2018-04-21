package com.shuanglong.attempt;

import java.util.List;

public class DataMiningAlgorithmDemo
{
	static private DataMiningAlgorithmDemo instance = new DataMiningAlgorithmDemo();
	public static void Enter()
	{
		instance.Start();
	}
	
	private void Start()
	{
		long start = System.currentTimeMillis();
        List<Point> points;
        
		try
		{
			System.out.println("------------ Before read points ------------");
			points = dbscanAnalysis.readFile();
			System.out.println("------------ After read begin cluster ------------");
	        dbscanAnalysis.cluster(points);
	        System.out.println("------------ After cluster begin save result ------------");
	        dbscanAnalysis.saveResult();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        long end = System.currentTimeMillis();
        System.out.println("use time:" + (end - start));
	}
}
