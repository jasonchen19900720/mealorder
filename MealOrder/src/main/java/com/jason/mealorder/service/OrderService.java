package com.jason.mealorder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jason.mealorder.entity.Order;

public interface OrderService {

	Map<String, Object> submitOrder(HttpServletRequest req);
	Map<String, Object> cancelOrder(Order order);
	Map<String, Object> userOrders(HttpServletRequest req);
	Map<String, Object> getCommentOrder(String orderId);
	Map<String, Object> confirmReceived(String orderId);
	//Map<String,Object> finishOrder(Order order);
	//Map<String,Object> getAvailableOrder();
	/*
	Map<String,Object> getOrderStatus(Order order);	
	Map<String,Object> showDoneOrder(String username);
	Map<String,Object> showUndoneOrder(String username);	
	*/
}
