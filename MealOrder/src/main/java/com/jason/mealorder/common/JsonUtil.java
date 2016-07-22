package com.jason.mealorder.common;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static Object jsonStrToObject(String jsonData,Class<?> objClass){
		
		Object obj=null;
		
		JSONObject jsonobj=JSONObject.fromObject(jsonData);
		
		obj=JSONObject.toBean(jsonobj, objClass);
		
		return obj;
	}
    public static String objectToJsonStr(Object obj){
    	
    	String jsonStr=null;
    	
    	JSONObject jsonobj=JSONObject.fromObject(obj);
    	
    	jsonStr=jsonobj.toString();
    	
		return jsonStr;
	}
    
    @SuppressWarnings("unchecked")
	public static <T> List<T> jsonStrToList(String jsonData,Class<T> objClass){
    	
    	JSONArray jsonArray=JSONArray.fromObject(jsonData);
    	
    	List<T> list=(List<T>)JSONArray.toCollection(jsonArray, objClass);
		
		return list;
	}
    
   public static <T> String listToJsonStr(List<T> list){
    	
    	JSONArray jsonArray=JSONArray.fromObject(list);
    	
    	String jsonArrayStr=jsonArray.toString();
		
		return jsonArrayStr;
	}    
}
