package com.jason.mealorder.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.entity.Dish;
import com.jason.mealorder.service.CommentService;
import com.jason.mealorder.service.DishService;
import com.jason.mealorder.service.OrderService;
import com.jason.mealorder.viewmodel.OrderModel;

@Controller
@RequestMapping(value="/order")
public class OrderAction {

	private static Logger log = Logger.getLogger(OrderAction.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private CommentService commentService;
	//进入菜单显示页面
	@RequestMapping(value="/mainMenu")
	public String showMainMenu(HttpServletRequest req,HttpServletResponse resp){
		log.info("显示主菜单详情");
		return "menu/mainMenu";
	}
	//进入菜系详细信息 
	@RequestMapping(value="/menu/{menuName}",method = RequestMethod.GET)     
	public String showSubMenu(@PathVariable("menuName") String menuName,HttpServletRequest req){ 
	   log.info("显示菜系菜单详情");	
	   //后续需要通过持久化来获取
	   return "menu/"+menuName;             
	 }  
	
	
	//进入菜品详细信息 
	@RequestMapping(value="/menu/dishDetail/{imgSrc}",method = RequestMethod.GET)     
	public String showCourseDetail(@PathVariable("imgSrc") String imgSrc,Model model){ 
		
		   log.info("showCourseDetail enter,courseName="+imgSrc);
		   Map<String,Object> resMap=dishService.obtainDishInfo(imgSrc);
		   if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
			   log.info("获取菜品详细信息发生错误");
			   return "error";
		   }else{
			   Dish dish=(Dish)resMap.get(SysConstant.DATA);
			   model.addAttribute("dish", dish);
			   return "courseDetail/courseDetail";
		   }		   
             
    }  
	@ResponseBody
	@RequestMapping(value="/comments")
    public String showComments(HttpServletRequest req){ 
		log.info("加载评论请求！");
    	Map<String,Object> resMap=commentService.getAllComments(req);   	
    	if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){ 
    		log.info("加载评论失败！");  		
    		return "{\"showComments\":\"error\"}";
    	}else{
    		 
    		log.info("加载评论成功！");
    		log.info(resMap.get(SysConstant.DATA).toString());
    		return resMap.get(SysConstant.DATA).toString();
    	}   	
    }
	//提交订单
	@ResponseBody
	@RequestMapping(value="/submitOrder")
	public String submitOrder(HttpServletRequest req){
		log.info("提交订单");			
		Map<String, Object> map = orderService.submitOrder(req);
		
		if(ResultCode.ERROR.getCode().equals(map.get(SysConstant.CODE))){
			log.info("提交订单发生错误");
			return "{\"submitOrder\":\"error\"}";
			
		}else{
			log.info("提交订单完成");
			return "{\"submitOrder\":\"success\"}";
		}
	}
	
	@RequestMapping(value="/addComment", method = RequestMethod.GET)
	public String publishComment(@RequestParam String comments,@RequestParam String orderId,
			                                                 Model model,HttpServletRequest req){	
		log.info("发表评论");
		log.info(orderId);
		Map<String,Object> resMap=commentService.addComment(comments,orderId,req);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
			model.addAttribute("resultMsg", "抱歉，评论失败，可能因为您之前已经参与评论！");
			return "result/error";
		}else{
			model.addAttribute("resultMsg", "评论成功，感谢你的评论！");
			return "result/success";
		}
		
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/myOrders" )
	public String userOrders(Model model,HttpServletRequest req){
		log.info("显示我的订单");
		Map<String,Object> resMap=orderService.userOrders(req);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
			log.info("加载我的订单异常");
			return "error";
		}else{
			log.info("加载我的订单成功");
			List<OrderModel> dataList=(List<OrderModel>)resMap.get(SysConstant.DATA);
			model.addAttribute("orders", dataList);
			return "order/userOrders";
		}
	}
	
	@RequestMapping(value="/toComment")
	public String toComment(@RequestParam String orderId,Model model){
		log.info("我要去评价");
		Map<String,Object> resMap=orderService.getCommentOrder(orderId);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
			log.info("加载评论订单异常");
			return "error";
		}else{
			log.info("加载评论订单成功");
			OrderModel orderModel=(OrderModel)resMap.get(SysConstant.DATA);
			model.addAttribute("orderModel", orderModel);
			return "order/addComment";
		}	
	}
	
	@RequestMapping(value="received")
	public String confirmDeliver(@RequestParam String orderId,Model model,HttpServletRequest req){
		log.info("确认收到订餐");
		Map<String,Object> resMap=orderService.confirmReceived(orderId);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
			model.addAttribute("resultMsg", "签收异常，可能你已经确认签收过了！");
			return "result/error";
		}else{
			model.addAttribute("resultMsg", "已经成功签收，感谢您对麦奥登支持！");
			return "result/success";
		}
	}
	
	
}
