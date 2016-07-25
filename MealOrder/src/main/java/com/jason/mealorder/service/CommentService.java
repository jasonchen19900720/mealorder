package com.jason.mealorder.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.mealorder.respmodel.RespModel;

public interface CommentService {

	RespModel getAllComments(HttpServletRequest req,HttpServletResponse resp);
	
	RespModel addComment(String comments,String orderId,HttpServletRequest req);
}
