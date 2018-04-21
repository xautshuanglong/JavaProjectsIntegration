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
    private static List<List<Point>> microCluster = new CopyOnWriteArrayList<List<Point>>();
    private static List<List<Double>> similarityMatrix= new ArrayList<List<Double>>();//存储原始数据集中任意两点之间的距离

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<Point> points = readFile();
        cluster(points);
        saveResult();
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
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\wine_result.txt"));
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
    		
    		//step2：分别划分r和t点的k-1个等价类
    		for(int j = 0;j < cluster.size();j++){
    			//在similarityMatrix中分别查询rPoint、tPoint和本簇中所有点的距离，并分别放着到两距离个List或者Array中，并用两个List分别记录距离对应的Point。
    			//对两者的距离进行升序排序，注意对距离数组排序后，也要改变point数组中的位置。
    			//对升序过程中改变了point位置的两个数组分别取前k-1个
    		}
    		
    	}
    }


}
