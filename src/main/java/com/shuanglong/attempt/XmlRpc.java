package com.shuanglong.attempt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XmlRpc {

	static public void XmlRpcClientTest() {
		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://localhost:8888/RPC2"));
			config.setConnectionTimeout(5000);

			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);

			ArrayList<String> params = new ArrayList<String>();
			params.add("flyoung");
			String result = (String) client.execute("get_string", params);
			System.out.println("result:" + result);
			
			Random forceRandom = new Random();
			ArrayList<Double[]> args = new ArrayList<>();
			Double[] realtime_force = new Double[6];
			for (int i=0; i<6; ++i)
			{
			    realtime_force[i] = forceRandom.nextDouble(); 
			}
			args.add(realtime_force);
			Object setResult = client.execute("set_realtime_force", args);
			System.out.println(setResult);
			
			ArrayList<Double[]> args2 = new ArrayList<>();
			Object getResult = client.execute("get_realtime_force", args2);
			if (getResult instanceof Object[])
            {
			    System.out.println("Bingo");
			    Object[] resultArray = (Object[]) getResult;
			    for (int i=0; i<resultArray.length; ++i)
			    {
			        System.out.println(resultArray[i]);
//			        double temp = (double)resultArray[i];
//			        System.out.println("Double: " + temp);
			    }
            }
			else
			{
			    System.out.println("False");
			    System.out.println(getResult);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}
}
