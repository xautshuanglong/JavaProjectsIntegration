package com.shuanglong.attempt;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

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
		
		Gson gson = new Gson();
		
		CiticJsonBean p = new CiticJsonBean();
		CiticJsonBean.TouBaoRen touBaoRen = p.new TouBaoRen();
		CiticJsonBean.BeiBaoRen beiBaoRen = p.new BeiBaoRen();
		p.setToubaoren(touBaoRen);
		p.setBeibaoren(beiBaoRen);
		
		beiBaoRen.setBbr_name("test111");
		touBaoRen.setTbr_name("test222");
		
        String json = gson.toJson(p);
        System.out.println(json);
        
        Gson gson2 = new Gson();
        CiticJsonBean p2 = gson2.fromJson(json, CiticJsonBean.class);
        System.out.println(p2.getBeibaoren().getBbr_name() + "   " + p2.getToubaoren().getTbr_name());
	}
}
