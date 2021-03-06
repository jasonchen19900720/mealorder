package com.jason.mealorder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.service.OrderService;
import com.jason.mealorder.viewmodel.OrderModel;

@Controller
@RequestMapping(value="/order")
public class OrderAction {

	private static Logger log = Logger.getLogger(OrderAction.class);
	
	@Autowired
	private OrderService orderService;
	
	
	//提交订单
	@ResponseBody
	@RequestMapping(value="/submitOrder")
	public String submitOrder(HttpServletRequest req){
		log.info("提交订单");			
		RespModel respModel = orderService.submitOrder(req);
		
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			log.info("提交订单发生错误");
			return "{\"submitOrder\":\"error\"}";
			
		}else{
			log.info("提交订单完成");
			return "{\"submitOrder\":\"success\"}";
		}
	}
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/myOrders" )
	public String userOrders(Model model,HttpServletRequest req){
		log.info("显示我的订单");
		RespModel respModel=orderService.userOrders(req);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			log.info("加载我的订单异常");
			return "error";
		}else{
			log.info("加载我的订单成功");
			List<OrderModel> dataList=(List<OrderModel>)respModel.getListData();
			model.addAttribute("orders", dataList);
			return "order/userOrders";
		}
	}
	
	@RequestMapping(value="/toComment")
	public String toComment(@RequestParam String orderId, Model model){
		log.info("我要去评价");
		RespModel respModel=orderService.getCommentOrder(orderId);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			log.info("加载评论订单异常");
			return "error";
		}else{
			log.info("加载评论订单成功");
			OrderModel orderModel=(OrderModel)respModel.getObjectData();
			model.addAttribute("orderModel", orderModel);
			return "order/addComment";
		}	
	}
	
	@RequestMapping(value="received")
	public String confirmDeliver(@RequestParam String orderId,Model model,HttpServletRequest req){
		log.info("确认收到订餐");
		RespModel respModel=orderService.confirmReceived(orderId);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			model.addAttribute("resultMsg", "签收异常，可能您已经确认签收过了！");
			return "result/error";
		}else{
			model.addAttribute("resultMsg", "已经成功签收，感谢您对麦奥登支持！");
			return "result/success";
		}
	}
	
	
}
