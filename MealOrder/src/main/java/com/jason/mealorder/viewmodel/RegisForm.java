package com.jason.mealorder.viewmodel;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class RegisForm {

	@NotBlank(message="输入姓名不能为空")
	@Size(min = 1, max = 10, message = "输入名字长度介于1~10个字符之间")
	private String name;
	
	@NotBlank(message="输入密码不能为空")
	@Size(min = 8, max = 20, message = "密码长度介于8~20个字符之间")
	private String password;
	
	@NotBlank(message = "确认密码不能为空")
	@Size(min = 8, max = 20, message = "确认密码长度介于8~20个字符之间")
	private String confirmedPassword;
	
	
	
	@NotBlank(message = "输入email不能为空")
    @Pattern(regexp = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$", message = "输入email格式不正确")
	private String email;
	
	
	@NotBlank(message = "手机号码不能为空")
	@Pattern(regexp = "^[1][3,4,5,8][0-9]{9}$",message="手机号码格式不正确")
	private String phoneNum;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString(RegisForm regisForm){
		
		return "["+getClass().getName()+"]"+"name="+regisForm.getName()+","+"password="+regisForm.getPassword()+
				","+"confirmedPassword="+regisForm.getConfirmedPassword()+","+"email="+regisForm.getEmail()+
				","+"phoneNum="+regisForm.getPhoneNum();
		
	}
}
