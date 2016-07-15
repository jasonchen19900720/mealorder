package com.jason.mealorder.mapper;

import java.util.List;

import com.jason.mealorder.entity.Order;

public interface OrderMapper {

	 void saveOrder(Order order);
	 void modifyOrder(Order order);	 
	 void updateOrderStatus(String orderStatus,String orderId); 
	 List<Order> getOrderByStatus(String orderStatus); 	 
	 List<Order> getUserOrders(String username);
	 Order getOrderById(String orderId);	 
	 
	 String getOrderStatusById(String orderId);
}
