package com.jason.mealorder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.service.CommentService;

@Controller
@RequestMapping(value="/comment")
public class CommentAction {

	private static Logger log = Logger.getLogger(CommentAction.class);
	@Autowired
	private CommentService commentService;
		
	@ResponseBody
	@RequestMapping(value="/showComments")
    public String showComments(HttpServletRequest req){ 
		log.info("������������");
    	Map<String,Object> resMap=commentService.getAllComments(req);   	
    	if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){ 
    		log.info("��������ʧ�ܣ�");  		
    		return "{\"showComments\":\"error\"}";
    	}else{
    		 
    		log.info("�������۳ɹ���");
    		log.info(resMap.get(SysConstant.DATA).toString());
    		return resMap.get(SysConstant.DATA).toString();
    	}   	
    }
	
	@RequestMapping(value="/addComment", method = RequestMethod.GET)
	public String publishComment(@RequestParam String comments,@RequestParam String orderId,
			                                                 Model model,HttpServletRequest req){	
		log.info("��������");
		log.info(orderId);
		Map<String,Object> resMap=commentService.addComment(comments,orderId,req);
		if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
			model.addAttribute("resultMsg", "��Ǹ������ʧ�ܣ�������Ϊ��֮ǰ�Ѿ��������ۣ�");
			return "result/error";
		}else{
			model.addAttribute("resultMsg", "���۳ɹ�����л������ۣ�");
			return "result/success";
		}
		
	}	
}
