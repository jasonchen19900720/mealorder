package com.jason.mealorder.service;

import javax.servlet.http.HttpServletRequest;

import com.jason.mealorder.entity.Order;
import com.jason.mealorder.respmodel.RespModel;

public interface OrderService {

	RespModel submitOrder(HttpServletRequest req);
	RespModel cancelOrder(Order order);
	RespModel userOrders(HttpServletRequest req);
	RespModel getCommentOrder(String orderId);
	RespModel confirmReceived(String orderId);
	//Map<String,Object> finishOrder(Order order);
	//Map<String,Object> getAvailableOrder();
	/*
	Map<String,Object> getOrderStatus(Order order);	
	Map<String,Object> showDoneOrder(String username);
	Map<String,Object> showUndoneOrder(String username);	
	*/
}
