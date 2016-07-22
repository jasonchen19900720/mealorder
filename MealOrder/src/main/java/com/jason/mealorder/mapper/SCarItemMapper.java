package com.jason.mealorder.mapper;

import java.util.List;

import com.jason.mealorder.entity.SCarItem;

public interface SCarItemMapper {

	public void addSCarItem(SCarItem item);
	public void deleteSCarItem(String userUuid,String goodsName);
	public void modifySCarItem(Integer amount,String userUuid,String goodsName);
	public SCarItem getSCarItem(String userUuid,String goodsName);	
	public List<SCarItem> getSCarAllItems(String userUuid);		
	public void clearSCar(String userUuid);
}
