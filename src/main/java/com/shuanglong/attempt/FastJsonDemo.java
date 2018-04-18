package com.shuanglong.attempt;

import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo
{
	static private FastJsonDemo instance = new FastJsonDemo();
	
	private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
	
	public FastJsonDemo()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static void Enter()
	{
		instance.JsonObjectTest();
	}
	
	private void JsonObjectTest()
	{
		JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
		System.out.println("Name:"+jsonObject.getString("studentName"));
		System.out.println("Age:"+jsonObject.getString("studentAge"));
		
		JSONObject testObj = new JSONObject();
		testObj.put("name", "张爱茹");
		testObj.put("age", 18);
		System.out.println(testObj.toJSONString());
	}
}
