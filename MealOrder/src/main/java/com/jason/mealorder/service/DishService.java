package com.jason.mealorder.service;

import com.jason.mealorder.respmodel.RespModel;

public interface DishService {

	RespModel obtainDishInfo(String attribution,String imgSrc);
}
