package com.jason.mealorder.service;

import javax.servlet.http.HttpServletRequest;

import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.viewmodel.RegisForm;

public interface UserService {

	 RespModel saveUser(RegisForm regisModel);	
	 RespModel userLogin(RegisForm regisForm,HttpServletRequest request);
	 RespModel validateUserEmail(String email);
     RespModel validateUserName(String name);
}
