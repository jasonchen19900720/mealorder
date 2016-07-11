package com.jason.mealorder.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.entity.Dish;
import com.jason.mealorder.mapper.DishMapper;

@Service
public class DishServiceImp implements DishService {

	private static Logger log = Logger.getLogger(DishServiceImp.class);
	
	@Autowired
	private DishMapper dishMapper;
	@Override
	public Map<String, Object> obtainDishInfo(String imgSrc) {
		log.info("获取菜品详情");
		Map<String, Object> map=new HashMap<String, Object>();
		try {		
			Dish dish= dishMapper.getDishByImgSrc(imgSrc);
			if(dish!=null){
				log.info(dish.getDishName()+","+dish.getPrice()+","+dish.getImgSrc());
				map.put(SysConstant.CODE, ResultCode.OK.getCode());	
				map.put(SysConstant.DATA,dish);
			}else{
				map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取菜品详情发生异常");
			map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
		}
		return map;
	}

}
