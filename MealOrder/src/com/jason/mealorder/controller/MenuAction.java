package com.jason.mealorder.controller;

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

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.entity.Dish;
import com.jason.mealorder.service.DishService;

@Controller
@RequestMapping(value="/menu")
public class MenuAction {

	private static Logger log = Logger.getLogger(MenuAction.class);
	
	@Autowired
	private DishService dishService;
	//进入菜单显示页面
	@RequestMapping(value="/mainMenu")
	public String showMainMenu(HttpServletRequest req,HttpServletResponse resp){
		log.info("显示主菜单详情");
		return "menu/mainMenu";
	}
	//进入菜系详细信息 
	@RequestMapping(value="/subMenu/{menuName}",method = RequestMethod.GET)     
	public String showSubMenu(@PathVariable("menuName") String menuName,HttpServletRequest req){ 
		  log.info("显示菜系菜单详情");	
		  //后续需要通过持久化来获取
		  return "menu/"+menuName;             
	}  
		
		
	//进入菜品详细信息 
	@RequestMapping(value="/dishDetail/{attribution}/{imgSrc}",method = RequestMethod.GET)     
	public String showDishDetail(@PathVariable("attribution") String attribution,
			                     @PathVariable("imgSrc") String imgSrc,Model model){ 
			
		log.info("showDishDetail enter,attribution="+attribution+",imgSrc="+imgSrc);
		Map<String,Object> resMap=dishService.obtainDishInfo(attribution,imgSrc);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
		    log.info("获取菜品详细信息发生错误");
			return "error";
		}else{
			Dish dish=(Dish)resMap.get(SysConstant.DATA);
			model.addAttribute("dish", dish);
			return "courseDetail/courseDetail";
		}		   
	             
    }  
}
