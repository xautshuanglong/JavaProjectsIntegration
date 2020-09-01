package com.shuanglong.attempt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XmlRpc
{
    private static Logger mgLogger = LogManager.getLogger(XmlRpc.class.getName());

    static public void XmlRpcClientTest()
    {
        try
        {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8888/RPC2"));
            config.setConnectionTimeout(5000);

            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            ArrayList<String> strParams = new ArrayList<String>();
            strParams.add("flyoung");
            String strRes = (String) client.execute("get_string", strParams);
            System.out.println("result:" + strRes);

            Random forceRandom = new Random();
            ArrayList<Double[]> args = new ArrayList<>();
            Double[] realtime_force = new Double[6];
            for (int i = 0; i < 6; ++i)
            {
                realtime_force[i] = forceRandom.nextDouble();
            }
            args.add(realtime_force);
            Object setResult = client.execute("set_realtime_force", args);
            System.out.println("set_realtime_force: " + setResult);

            ArrayList<Double[]> args2 = new ArrayList<>();
            Object getResult = client.execute("get_realtime_force", args2);
            if (getResult instanceof Object[])
            {
                Object[] resultArray = (Object[]) getResult;
                for (int i = 0; i < resultArray.length; ++i)
                {
                    System.out.println(resultArray[i]);
                }
            }
            else
            {
                System.out.println("False");
                System.out.println(getResult);
            }

            ArrayList<Integer> intParams = new ArrayList<Integer>();
            intParams.add(300);
            client.execute("set_data", intParams);
            intParams.clear();
            Integer intRes = (Integer) client.execute("get_data", intParams);
            System.out.println("set_data 300, get_data " + intRes);

            intParams.clear();
            intParams.add(3);
            intParams.add(6);
            intRes = (Integer) client.execute("add", intParams);
            System.out.println(intParams.get(0) + " + " + intParams.get(1) + " = " + intRes);

            intParams.clear();
            intParams.add(3);
            intParams.add(5);
            Double divRes = (Double) client.execute("div", intParams);
            System.out.println(intParams.get(0) + " / " + intParams.get(1) + " = " + divRes);

            intParams.clear();
            intParams.add(3);
            intParams.add(5);
            intRes = (Integer) client.execute("math_add", intParams);
            System.out.println(intParams.get(0) + " + " + intParams.get(1) + " = " + intRes);

            intParams.clear();
            intParams.add(3);
            intParams.add(4);
//            params.add(0);
            divRes = (Double) client.execute("math_div", intParams);
            System.out.println(intParams.get(0) + " / " + intParams.get(1) + " = " + divRes);
        } catch (MalformedURLException e)
        {
            String errorMsg = "Malformed URL";
            StackTraceElement[] stackTraces = e.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces)
            {
                errorMsg += "\n\tat " + stackTrace.toString();
            }
            System.out.println(errorMsg);
            mgLogger.error(errorMsg);
        } catch (XmlRpcException e)
        {
            String errorMsg = "XML RPC Error: ";
            StackTraceElement[] stackTraces = e.getStackTrace();
            for (StackTraceElement stackTrace : stackTraces)
            {
                errorMsg += "\n\tat " + stackTrace.toString();
            }
            mgLogger.error(errorMsg);
        }
    }
}
