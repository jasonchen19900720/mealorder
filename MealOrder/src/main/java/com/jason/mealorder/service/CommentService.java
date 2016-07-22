package com.jason.mealorder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommentService {

	public Map<String,Object> getAllComments(HttpServletRequest req,HttpServletResponse resp);
	
	public Map<String,Object> addComment(String comments,String orderId,HttpServletRequest req);
}
