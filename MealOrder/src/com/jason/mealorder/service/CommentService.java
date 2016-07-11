package com.jason.mealorder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {

	public Map<String,Object> getAllComments(HttpServletRequest req);
	
	public Map<String,Object> addComment(String comments,String orderId,HttpServletRequest req);
}
