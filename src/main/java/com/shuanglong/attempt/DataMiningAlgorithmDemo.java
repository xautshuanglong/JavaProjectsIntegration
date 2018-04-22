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
	        System.out.println("------------ After cluster begin partition ------------");
	        dbscanAnalysis.saveResult();
	        System.out.println("---------------- Begin k partition ----------------");
	        dbscanAnalysis.K_Partition();
	        System.out.println("------------ After partition begin save result ------------");
	        dbscanAnalysis.savePartitionResult();
	        System.out.println("------------ Begin add laplace noice and save result ------------");
	        dbscanAnalysis.addLaplaceNoise();
	        dbscanAnalysis.saveNoiseResult();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        long end = System.currentTimeMillis();
        System.out.println("use time:" + (end - start));
	}
}
