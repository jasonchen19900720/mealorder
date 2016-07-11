package com.jason.mealorder.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.mealorder.common.JsonUtil;
import com.jason.mealorder.common.OrderNumProducer;
import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.OrderStatus;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.entity.Order;
import com.jason.mealorder.entity.User;
import com.jason.mealorder.mapper.OrderMapper;
import com.jason.mealorder.mapper.SCarItemMapper;
import com.jason.mealorder.viewmodel.GoodsItem;
import com.jason.mealorder.viewmodel.OrderModel;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private SCarItemMapper sCarItemMapper;
	private Logger log = Logger.getLogger(OrderServiceImp.class);
	@Override
	public Map<String, Object> submitOrder(HttpServletRequest req) {
		Map<String,Object> map= new HashMap<String, Object>();	
		map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
		User curUser=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
		if(curUser==null){
			log.info("用户未登录或登录超时");		
			return map;
		}
	    try{				
			 String orderInfo=req.getParameter("orderInfo");
		     log.info(orderInfo);
		     List<GoodsItem> list=JsonUtil.jsonStrToList(orderInfo, GoodsItem.class);	
		     Integer totalPrice=0;	
		     for(GoodsItem g: list){		
			    totalPrice+=g.getAmount()*g.getPrice();		
		     }	
		     Order order = new Order();
		     order.setUsername(curUser.getName());	     
		     order.setAddress(req.getParameter("address"));
		     order.setOrderInfo(orderInfo);
		     order.setPhoneNum(req.getParameter("phoneNum"));
		     order.setOrderId(OrderNumProducer.getOrderNum());	
		     order.setOrderStatus(OrderStatus.WAITING.getStatus());
		     order.setTotalPrice(totalPrice);
		     order.setNotes(req.getParameter("userNotes"));
		     order.setSubmitTime(new Timestamp(System.currentTimeMillis()));		    
		     log.info("orderInfo="+order.getOrderInfo());		    
		     orderMapper.saveOrder(order);	
		     sCarItemMapper.clearSCar(curUser.getUserUuid());
		     map.put(SysConstant.CODE, ResultCode.OK.getCode());
		}catch(Exception e){
			 e.printStackTrace();
			 log.error("保存订单发生异常");
			 map.put(SysConstant.CODE, ResultCode.ERROR.getCode());		
		}
		return map;
	}

	@Override
	public Map<String, Object> cancelOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> userOrders(HttpServletRequest req) {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
		User curUser=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
		if(curUser==null){
			log.info("用户未登录或登录超时");		
			return map;
		}else{
			try {
				List<Order> list=orderMapper.getUserOrders(curUser.getName());
				List<OrderModel> dataList= new ArrayList<OrderModel>();
				for(Order o:list){
					OrderModel orderModel = new OrderModel(o);
					dataList.add(orderModel);
				}
				map.put(SysConstant.CODE, ResultCode.OK.getCode());
				map.put(SysConstant.DATA, dataList);//JsonUtil.listToJsonStr(dataList)
			} catch (Exception e) {
				e.printStackTrace();
				map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
			}
		}
		return map;
	}
	
	public Map<String, Object> getCommentOrder(String orderId){
		Map<String,Object> map= new HashMap<String, Object>();
		try {
			Order order=orderMapper.getOrderById(orderId);
			OrderModel orderModel = new OrderModel(order);
			map.put(SysConstant.DATA, orderModel);
			map.put(SysConstant.CODE, ResultCode.OK.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
		}
		return map;
	}

	@Override
	public Map<String, Object> confirmReceived(String orderId) {
		Map<String,Object> map= new HashMap<String, Object>();
		try {		
			String status=orderMapper.getOrderStatusById(orderId);
			log.info(status);
			if(OrderStatus.DONE.getStatus().equals(status)){
				map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
				return map;
			}			
			orderMapper.updateOrderStatus(OrderStatus.DONE.getStatus(),orderId);
			map.put(SysConstant.CODE, ResultCode.OK.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
		}
		return map;
	}
	
	

}
