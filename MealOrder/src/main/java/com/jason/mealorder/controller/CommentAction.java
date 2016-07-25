package com.jason.mealorder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.service.CommentService;

@Controller
@RequestMapping(value="/comment")
public class CommentAction {

	private static Logger log = Logger.getLogger(CommentAction.class);
	@Autowired
	private CommentService commentService;
			
	@RequestMapping(value="/showComments",produces = "application/json; charset=utf-8")
	@ResponseBody
    public String showComments(HttpServletRequest req,HttpServletResponse resp){ 
		log.info("加载评论");
    	RespModel respModel=commentService.getAllComments(req,resp);   	
    	if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){ 
    		log.info("加载评论异常");  		
    		return "{\"showComments\":\"error\"}";
    	}else{
    		 
    		log.info("加载评论成功");
    		log.info(respModel.getJsonStrData());
    		return respModel.getJsonStrData();
    	}   	
    }
	
	@RequestMapping(value="/addComment", method = RequestMethod.GET)
	public String publishComment(@RequestParam String comments,@RequestParam String orderId,
			                                                 Model model,HttpServletRequest req){	
		log.info("发表评论");
		log.info(orderId);
		RespModel respModel=commentService.addComment(comments,orderId,req);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			model.addAttribute("resultMsg", "发表评论失败");
			return "result/error";
		}else{
			model.addAttribute("resultMsg", "发表评论成功");
			return "result/success";
		}
		
	}	
}
