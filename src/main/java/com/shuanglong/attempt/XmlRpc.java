package com.shuanglong.attempt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}
}
