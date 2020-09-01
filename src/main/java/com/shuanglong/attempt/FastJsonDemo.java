package com.shuanglong.attempt;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class FastJsonDemo
{
    static private FastJsonDemo instance = new FastJsonDemo();

    private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

    public FastJsonDemo()
    {
    }

    public static void Enter()
    {
        instance.JsonObjectTest();
    }

    private void JsonObjectTest()
    {
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        System.out.println("Name:" + jsonObject.getString("studentName"));
        System.out.println("Age:" + jsonObject.getString("studentAge"));

        JSONObject testObj = new JSONObject();
        testObj.put("name", "zhangar");
        testObj.put("age", 18);
        System.out.println(testObj.toJSONString());

        Gson gson = new GsonBuilder().serializeNulls().create();
        List<CiticJsonBean> pList = new ArrayList<CiticJsonBean>();
        CiticJsonBean p = new CiticJsonBean();
        CiticJsonBean.TouBaoRen touBaoRen = p.new TouBaoRen();
        CiticJsonBean.BeiBaoRen beiBaoRen = p.new BeiBaoRen();
        p.setToubaoren(touBaoRen);
        p.setBeibaoren(beiBaoRen);
        beiBaoRen.setBbr_name("test111");
        touBaoRen.setTbr_name("test222");
        pList.add(p);

        p = new CiticJsonBean();
        touBaoRen = p.new TouBaoRen();
        beiBaoRen = p.new BeiBaoRen();
        p.setToubaoren(touBaoRen);
        p.setBeibaoren(beiBaoRen);
        beiBaoRen.setBbr_name("test333");
        touBaoRen.setTbr_name("test444");
        pList.add(p);

        String json = gson.toJson(pList);
        System.out.println(json);

        Gson gsonPrettyPrint = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        String jsonPretty = gsonPrettyPrint.toJson(pList);
        System.out.println(jsonPretty);

        Gson gson2 = new Gson();
        List<CiticJsonBean> pList2 = gson2.fromJson(json, new TypeToken<List<CiticJsonBean>>()
        {
        }.getType());
        for (CiticJsonBean pItem : pList2)
        {
            System.out.println(pItem.getBeibaoren().getBbr_name() + "   " + pItem.getToubaoren().getTbr_name());
        }
    }
}
