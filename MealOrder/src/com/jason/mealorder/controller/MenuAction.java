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
	//����˵���ʾҳ��
	@RequestMapping(value="/mainMenu")
	public String showMainMenu(HttpServletRequest req,HttpServletResponse resp){
		log.info("��ʾ���˵�����");
		return "menu/mainMenu";
	}
	//�����ϵ��ϸ��Ϣ 
	@RequestMapping(value="/subMenu/{menuName}",method = RequestMethod.GET)     
	public String showSubMenu(@PathVariable("menuName") String menuName,HttpServletRequest req){ 
		  log.info("��ʾ��ϵ�˵�����");	
		  //������Ҫͨ���־û�����ȡ
		  return "menu/"+menuName;             
	}  
		
		
	//�����Ʒ��ϸ��Ϣ 
	@RequestMapping(value="/dishDetail/{attribution}/{imgSrc}",method = RequestMethod.GET)     
	public String showDishDetail(@PathVariable("attribution") String attribution,
			                     @PathVariable("imgSrc") String imgSrc,Model model){ 
			
		log.info("showDishDetail enter,attribution="+attribution+",imgSrc="+imgSrc);
		Map<String,Object> resMap=dishService.obtainDishInfo(attribution,imgSrc);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
		    log.info("��ȡ��Ʒ��ϸ��Ϣ��������");
			return "error";
		}else{
			Dish dish=(Dish)resMap.get(SysConstant.DATA);
			model.addAttribute("dish", dish);
			return "courseDetail/courseDetail";
		}		   
	             
    }  
}
