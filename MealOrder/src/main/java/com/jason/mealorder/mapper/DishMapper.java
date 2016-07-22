package com.jason.mealorder.mapper;

import com.jason.mealorder.entity.Dish;

public interface DishMapper {

	void addDish(Dish dish);
	Dish getDish(String attribution,String imgSrc);
	String getImgSrc(String attribution,String dishName);
	//void modifyDishPrice(String dishName,String price);
	//void modifyDishImgSrc(String dishName,String imgSrc);
}
