package com.jason.mealorder.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jason.mealorder.viewmodel.UserModel;

@Controller
@RequestMapping(value="/test")
public class HelloController {

	private static final Logger log = Logger.getLogger(HelloController.class);
	
	@ModelAttribute("user1")
	public UserModel getUser( String username) {
    log.info("ModelAttribute handle first");
	UserModel user1 = new UserModel();
	user1.setUsername("hexinyi");
	user1.setPassword("woaini");
	     return user1;
	} 
	
	@RequestMapping(value="/test")
	public String test(){
		
		return "hello";
	}  
	
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public String sayHello(/*@ModelAttribute("user2")*/ UserModel user, Model model){
		log.info("username:"+user.getUsername());
		log.info("password:"+user.getPassword());
		log.info("email:"+user.getEmail());
		model.addAttribute("string", "rinima");
		return "hello";
	}
}
