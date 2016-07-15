package com.jason.mealorder.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.common.SysEnum.ResultMsg;
import com.jason.mealorder.entity.User;
import com.jason.mealorder.service.UserService;
import com.jason.mealorder.viewmodel.RegisForm;



@Controller
@RequestMapping(value="/user")
public class UserAction {

	private static Logger log= Logger.getLogger(UserAction.class);
	@Autowired
	private UserService userService;	
	
	//注册页面进行用户注册
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@Valid RegisForm regisForm, BindingResult bindingResult,Model model,HttpServletRequest req){
		if (bindingResult.hasErrors()) {
			 FieldError fieldError = bindingResult.getFieldError();
			 String fieldName=fieldError.getField();
			 String validMsg;
			 try {
				 validMsg = new String(fieldError.getDefaultMessage().getBytes("utf-8"),"utf-8");
				 log.info(validMsg);
				 model.addAttribute(fieldName+"Valid",validMsg);
			 } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			 }
			
		}  
		log.info(regisForm.toString(regisForm));
		Map<String,Object> map =userService.saveUser(regisForm);
		if(ResultCode.ERROR.getCode().equals(map.get(SysConstant.CODE))){
			log.info("注册失败");
			return "error";
		}else{
		    log.info("注册成功！");	    
		    return "success";
		}
	}
	
    //进入注册页面 
    @RequestMapping(value = "/toRegister")
    public String toRegister( Model model,HttpServletRequest request) {
        request.getSession().removeAttribute(SysConstant.CURRENT_USER);
        log.info("进入注册页面");
        return "user/register";
    }
    
    //进入登录页面
    @RequestMapping(value = "/toLogin")
    public String toLogin( Model model,HttpServletRequest request){
    	request.getSession().removeAttribute(SysConstant.CURRENT_USER);
    	log.info("进入登录页面");
		return "user/login";		
	}
    
    //登录页面进行用户登录
    @RequestMapping(value = "/login")
    public String login(RegisForm regisForm,Model model,HttpServletRequest request){
    	log.info(regisForm.toString(regisForm));
    	Map<String,Object> resMap=userService.userLogin(regisForm, request);
    	if(ResultCode.ERROR.getCode().equals(resMap.get(SysConstant.CODE))){
    		log.error("登录失败");	
    		model.addAttribute("loginMsg", resMap.get(SysConstant.MSG));
    		return "user/login";
    	}else{
    		log.info("登陆成功");    		
    		return "main";
    	}    	
    }
    
    //进入主页
    @RequestMapping(value = "/toMain")
    public String toMainPage(){
    	log.info("进入主页");
    	return "main";
    }
    
    //退出登录
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
    	request.getSession().removeAttribute(SysConstant.CURRENT_USER);  	
    	return "welcome";
    }
  
    //我的资料
    @RequestMapping(value="/userInfo")
    public String userInfo(Model model,HttpServletRequest request){    	
    	User user=(User)request.getSession().getAttribute(SysConstant.CURRENT_USER);
    	if(null==user){
    		model.addAttribute("loginMsg",ResultMsg.LOGIN_TIMEOUT.getMsg());
    		return "user/login";
    	}
    	return "user/userInfo";
    }
    
    //进入修改密码页面
    @RequestMapping(value="/toModifyPwd")
    public String toModifyPwd(Model model,HttpServletRequest request){    	
    	User user=(User)request.getSession().getAttribute(SysConstant.CURRENT_USER);
    	if(null==user){
    		model.addAttribute("loginMsg",ResultMsg.LOGIN_TIMEOUT.getMsg());
    		return "user/login";
    	}
    	return "user/modifyPwd";
    }
    //校验邮箱是否被注册
    @ResponseBody
    @RequestMapping(value = "/validateEmail", produces = "application/json;charset=UTF-8")
    public String validateUserEmail(@RequestParam(value="email",required =true,defaultValue="")String email){
    	log.info("校验邮箱："+email);
    	Map<String, Object> map = userService.validateUserEmail(email);
    	
    	if ("-1".equals(map.get(SysConstant.CODE))) {
            return "{\"valid\":false}";
        } else {
            return "{\"valid\":true}";
        }
    }
    
  //校验用户名是否被注册   
    @ResponseBody
    @RequestMapping(value = "/validateName", produces = "application/json;charset=UTF-8")
    public String validateUserName(@RequestParam(value="name",required =true,defaultValue="")String name){
    	 log.info("校验名字："+name);
    	 Map<String, Object> map = userService.validateUserName(name);

         if ("-1".equals(map.get(SysConstant.CODE))) {
        	 log.info("名字已被注册");
             return "{\"valid\":false}";
         } else {
             return "{\"valid\":true}";
         }
    	
    }
    

}
