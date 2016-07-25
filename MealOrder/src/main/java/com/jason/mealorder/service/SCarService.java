package com.jason.mealorder.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.viewmodel.GoodsItem;

public interface SCarService {
	 
	 RespModel addGoods(GoodsItem item,HttpServletRequest req);
	 RespModel removeGoods(String goodsName,HttpServletRequest req);
	 RespModel modifyGoods(GoodsItem item,HttpServletRequest req,HttpServletResponse resp);
	 RespModel showSCar(HttpServletRequest req);
}
