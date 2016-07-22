package com.jason.mealorder.mapper;

import java.util.List;

import com.jason.mealorder.entity.User;

public interface UserMapper {
	
	public void saveUser(User user);
	
	public List<User> validateUserName(String name);
	
	public List<User> validateUserEmail(String email);
	
	public List<User> validateLogin(String email,String password);
}
