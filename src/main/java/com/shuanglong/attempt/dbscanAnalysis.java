package com.shuanglong.attempt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class dbscanAnalysis {
    //    private static final double r = 0.05; // 半径
    private static final double radius = 20; // 半径
    private static final int minPoints = 5;// 密度阈值
    private static final int kPart = 5;// 划分尺寸大小
    private static List<List<Point>> microCluster = new CopyOnWriteArrayList<List<Point>>();
    private static List<List<Double>> similarityMatrix= new ArrayList<List<Double>>();//存储原始数据集中任意两点之间的距离
    private static List<List<Point>> kPartitionResult = new ArrayList<List<Point>>();// 存储 K 划分的结果

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Point> points = readFile();
        cluster(points);
        saveResult();
        K_Partition();
        savePartitionResult();
        addLaplaceNoise();
        saveNoiseResult();
        long end = System.currentTimeMillis();
        System.out.println("use time:" + (end - start));
    }


    public static double Noice() {
        double d = Math.random();
        double uniform = (double) d / 1 - 0.5;
        double ss = -(1 / 0.5) * Math.signum(uniform) * Math.log(1 - 2 * Math.abs(uniform));
        return ss;
    }

    /**
     * 从文件中读取数据
     *
     * @return
     * @throws Exception
     */
    public static List<Point> readFile() throws Exception {
//        BufferedReader file = new BufferedReader(new FileReader("E:\\Memory\\ES\\homework\\dbscan\\magic.txt"));
//        BufferedReader file = new BufferedReader(new FileReader("src\\com\\wmq\\wine.txt"));
    	BufferedReader file = new BufferedReader(new FileReader("src\\main\\resources\\wine.txt"));
        List<Point> points = new ArrayList<Point>();
        String line = file.readLine();
        int i = 0;
        while (line != null) {
            String p[] = line.split("[;|,|g|h|\\s]");
            List<Double> location = new ArrayList<Double>();
            for (int j = 0; j < p.length; j++) {
                location.add(Double.parseDouble(p[j]));
            }
            points.add(new Point(i++, location, 0));
            line = file.readLine();
        }
        file.close();
        file = null;
        return points;
    }

    /**
     * 保存结果数据到文件中
     *
     * @throws Exception
     */
    public static void saveResult() throws Exception {
//        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\com\\wmq\\magic1.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\wine_dbscan_result.txt"));
        int i = 1;
        for (List<Point> mic : microCluster) {
            bw.write("the " + i + " microCluster:\r\n");
            for (Point p : mic) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < p.getLocation().size(); j++) {
                    sb.append(p.getLocation().get(j) + ",");
                }
                bw.write(sb.append(i).toString());
                bw.newLine();
            }
            i++;
        }
        bw.flush();
        bw.close();
    }
    
    public static void savePartitionResult() throws Exception {
      BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\wine_kpartition_result.txt"));
      int i = 1;
      for (List<Point> mic : kPartitionResult) {
          bw.write("the " + i + " partition:\r\n");
          for (Point p : mic) {
              StringBuffer sb = new StringBuffer();
              for (int j = 0; j < p.getLocation().size(); j++) {
                  sb.append(p.getLocation().get(j) + ",");
              }
              bw.write(sb.append(i).toString());
              bw.newLine();
          }
          i++;
      }
      bw.flush();
      bw.close();
  }
    
	public static void saveNoiseResult() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\wine_noise_result.txt"));
        int i = 1;
        for (List<Point> mic : kPartitionResult) {
            bw.write("the " + i + " partition with noise:\r\n");
            for (Point p : mic) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < p.getLocation().size(); j++) {
                    sb.append(p.getLocation().get(j) + ",");
                }
                bw.write(sb.append(i).toString());
                bw.newLine();
            }
            i++;
        }
        bw.flush();
        bw.close();
    }

    public static double getDistance(Point point1, Point point2) {
        int wide = point1.getLocation().size(); // 共多少维
        double sum = 0;
        for (int i = 0; i < wide; i++) {
            sum += Math.pow((point1.getLocation().get(i) - point2.getLocation().get(i)), 2);
        }
//        Double.parseDouble(point1.getLocation().toString())
        return Math.sqrt(sum);
    }

    public static List<Double> getDistances(Point point1, List<Point> points) {
        List<Double> diss = new ArrayList<Double>();
        int size = points.size();
        for (int i = 0; i < size; i++)
            diss.add(getDistance(point1, points.get(i)));
        return diss;
    }

    public static boolean canCombine(List<Point> clu1, List<Point> clu2) {
        Set<Point> result = new HashSet<Point>();
        Set<Point> s1 = new HashSet<Point>(clu1);
        Set<Point> s2 = new HashSet<Point>(clu2);
        result.clear();
        result.addAll(s1);
        result.retainAll(s2);//求交集
        if (result.size() > 0) {
            return true;
        }
        return false;
    }

    public static void combine(List<Point> clu) {
        List<Integer> combine = new ArrayList<Integer>();
        for (int i = 0; i < microCluster.size(); i++) {
            if (canCombine(clu, microCluster.get(i)))
                combine.add(i);
        }
        Set<Point> com = new HashSet<Point>();
        List<List<Point>> remove = new ArrayList<List<Point>>();
        for (int i = 0; i < combine.size(); i++) {
            List<Point> p = microCluster.get(combine.get(i));
            com.addAll(p);
            remove.add(p);
        }
        microCluster.removeAll(remove);
        microCluster.add(new ArrayList<Point>(com));
    }

    public static void cluster(List<Point> points) {
        int size = points.size();
        for (int i = 0; i < size; i++) {
        	
        	System.out.println("dbscanAnalysis.java cluster --> " + size +" - " + i);
        	
            Point p = points.get(i);
            if (p.getFlag() != 0)
                continue;

            p.setFlag(1);
            List<Double> diss = getDistances(p, points);//计算p与其他所有点的距�?
            List<Point> clu = new ArrayList<Point>();//临域集合
            similarityMatrix.add(diss);
            for (int j = 0; j < size; j++) {
                if (diss.get(j) < radius && (points.get(j).getFlag()==0)) {
                    clu.add(points.get(j)); //若距离小于r
                }
            }
            if (clu.size() < minPoints)
                continue;
            else {
                microCluster.add(clu);//加入新簇
                combine(clu);//合并
            }
        }
    }
    
    public static void K_Partition(){//k划分
    	for(int i = 0;i < microCluster.size();i++){//对每一个簇进行k划分
    		List<Point> cluster = microCluster.get(i);
    		
    		// step1:寻找当前簇中距离最远的两个点r和t
    		Point rPoint = null;
    		Point tPoint = null;
    		double maxDistance = 0;
    		for(int j = 0;j < cluster.size();j++){
    			Point pointj = cluster.get(j);
    			for(int k = 0;k < cluster.size();k++){
    				Point pointk = cluster.get(k);
    				if(similarityMatrix.get(pointj.getId()).get(pointk.getId()) > maxDistance){
    					maxDistance = similarityMatrix.get(pointj.getId()).get(pointk.getId());
    					rPoint = pointj;
    		    		tPoint = pointk;
    				}
    			}
    		}
    		
    		// step2-6
//    		Set<Point> allSet = new HashSet<Point>(cluster);
    		List<Point> tempClusterCopy = new ArrayList<Point>(cluster); // 拷贝 --> 防止破坏原聚类结果集
    		while(tempClusterCopy.size() > 0) {
    			if (tempClusterCopy.size() < kPart) {
    				// < k （划分到离自己最近的等价类中，在所有已划分的等价类中搜索最近的？？？）
    				Point curPoint = tempClusterCopy.get(0);
    				List<Point> assumePart = kPartitionResult.get(0);
    				assumePart.add(curPoint);
    				
    				List<Double> disList = null;
    				Double assumeDis = Double.MAX_VALUE; // 假定最小距离（点到划分类）
    				Double disSum = 0.0;
    				
    				for (int n=1; n < tempClusterCopy.size(); ++n) {
    					curPoint = tempClusterCopy.get(n);
    					for (List<Point> partition : kPartitionResult) {
    						// 最近的等价类？？？
    						disSum = 0.0;
    						disList = getDistances(curPoint, partition);
    						for(Double dis : disList)
    							disSum+=dis;
    						if (disSum < assumeDis)
    						{
    							assumeDis = disSum;
    							assumePart.remove(curPoint);
    							assumePart = partition;
    							assumePart.add(curPoint);
    						}
    					}
    				}
    				tempClusterCopy.clear();
				}
    			else if (tempClusterCopy.size() < 2 * kPart){
    				// < 2k 自成一类
    				List<Point> partList = new ArrayList<Point>();
    				partList.addAll(tempClusterCopy);
    				tempClusterCopy.clear();
    				kPartitionResult.add(partList);
    			}
    			else {
					// >= 2k 分别以 r和t 为中心划分等价类
    				List<Point> partSetR = new ArrayList<Point>();
    				List<Point> partSetT = new ArrayList<Point>();
    				Point tempPoint = null;
    				for(int m = 0; m < kPart; ++m) {
    					tempPoint = getShortestPoint(rPoint, tempClusterCopy);
    					if (tempPoint != null) {
							partSetR.add(tempPoint);
							tempClusterCopy.remove(tempPoint);
						}
    					tempPoint = getShortestPoint(tPoint, tempClusterCopy);
    					if (tempPoint != null) {
							partSetT.add(tempPoint);
							tempClusterCopy.remove(tempPoint);
						}
    				}
    				kPartitionResult.add(partSetR);
    				kPartitionResult.add(partSetT);
				}
    			
    			System.out.println("Kpartition allSet.size()="+tempClusterCopy.size());
    		}
    		tempClusterCopy = null;
    	}
    }
    
    public static Point getShortestPoint(Point centerPoint, List<Point> cluster) {
    	Point retValue = null;
    	Point targetPoint = null;
    	Double distance = Double.MAX_VALUE;
    	Double tempDistance = 0.0;
    	for (int i=0; i<cluster.size(); ++i)
    	{
    		targetPoint = cluster.get(i);
    		tempDistance = similarityMatrix.get(centerPoint.getId()).get(targetPoint.getId());
    		if(tempDistance < distance){
				distance = tempDistance;
				retValue = targetPoint;
			}
    	}
    	return retValue;
    }
    
    public static void addLaplaceNoise() {
    	for (List<Point> partition : kPartitionResult) {
    		
    		Double pointsSum = 0.0; // 等价类中元祖总和
    		int pointCount = 0; // 等价类中元组个数
    		
    		int partSize = partition.size();
    		int locationSize = 0;
    		
    		for (int i=0; i<partSize; ++i) {
    			Point point = partition.get(i);
    			List<Double> location = point.getLocation();
    			locationSize = location.size();
    			for (int j=0; j<locationSize; ++j) {
    				pointsSum += location.get(j);
    			}
    			pointCount += locationSize;
    		}
    		Double noise = Noice();
    		Double xNoise = (pointsSum + noise) / (pointCount + noise);
    		for (int i=0; i<partSize; ++i) {
    			Point point = partition.get(i);
    			List<Double> location = point.getLocation();
    			locationSize = location.size();
    			for (int j=0; j<locationSize; ++j) {
    				location.set(j, xNoise);
    			}
    		}
    	}
    }
}
