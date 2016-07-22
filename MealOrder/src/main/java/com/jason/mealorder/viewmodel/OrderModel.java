package com.jason.mealorder.viewmodel;

import java.util.List;

import com.jason.mealorder.common.JsonUtil;
import com.jason.mealorder.entity.Order;

public class OrderModel {

	public OrderModel(){}
	
	public OrderModel(Order order){
		
		this.address=order.getAddress();
		this.notes=order.getNotes();
		this.orderId=order.getOrderId();
		this.setOrderInfoList(JsonUtil.jsonStrToList(order.getOrderInfo(), GoodsItem.class));
		this.orderStatus=order.getOrderStatus();
		this.phoneNum=order.getPhoneNum();
		this.totalPrice=order.getTotalPrice();
		this.username=order.getUsername();
		this.submitTime=order.getSubmitTime().toString().substring(0, 16);
	}
		
	private String orderId;
	private String username;
    private String phoneNum;
    private List<GoodsItem> orderInfoList;
    private String address;    
    private String orderStatus;
    private String notes;  
    private Integer totalPrice;
    private String submitTime;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public List<GoodsItem> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<GoodsItem> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}
	
}
