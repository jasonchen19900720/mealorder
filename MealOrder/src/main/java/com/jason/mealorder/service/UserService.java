package com.jason.mealorder.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jason.mealorder.viewmodel.RegisForm;

public interface UserService {

	public Map<String, Object> saveUser(RegisForm regisModel);	
	public Map<String, Object> userLogin(RegisForm regisForm,HttpServletRequest request);
	public Map<String, Object> validateUserEmail(String email);
    public Map<String, Object> validateUserName(String name);
}
