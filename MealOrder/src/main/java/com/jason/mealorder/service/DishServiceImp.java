package com.jason.mealorder.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.entity.Dish;
import com.jason.mealorder.mapper.DishMapper;
import com.jason.mealorder.respmodel.RespModel;

@Service
public class DishServiceImp implements DishService {

	private static Logger log = Logger.getLogger(DishServiceImp.class);
	
	@Autowired
	private DishMapper dishMapper;
	public RespModel obtainDishInfo(String attribution,String imgSrc) {
		log.info("获取菜品详情");
		RespModel respModel = new RespModel();
		try {		
			Dish dish= dishMapper.getDish(attribution,imgSrc);
			if(dish!=null){
				log.info(dish.getDishName()+","+dish.getPrice()+","+dish.getImgSrc());
				respModel.setResultCode(ResultCode.OK.getCode());	
				respModel.setObjectData(dish);
			}else{
				respModel.setResultCode(ResultCode.ERROR.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取菜品详情发生异常");
			respModel.setResultCode(ResultCode.ERROR.getCode());
		}
		return respModel;
	}

}
