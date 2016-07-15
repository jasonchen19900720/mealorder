package com.jason.mealorder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.mealorder.viewmodel.GoodsItem;

public interface SCarService {
	 
	 Map<String, Object> addGoods(GoodsItem item,HttpServletRequest req);
	 Map<String, Object> removeGoods(String goodsName,HttpServletRequest req);
	 Map<String, Object> modifyGoods(GoodsItem item,HttpServletRequest req,HttpServletResponse resp);
	 Map<String, Object> showSCar(HttpServletRequest req);
}
