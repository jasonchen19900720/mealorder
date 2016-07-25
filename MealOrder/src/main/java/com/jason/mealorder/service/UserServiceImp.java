package com.jason.mealorder.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.mealorder.common.MD5Util;
import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.common.SysEnum.ResultMsg;
import com.jason.mealorder.entity.User;
import com.jason.mealorder.mapper.UserMapper;
import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.viewmodel.RegisForm;

@Service
public class UserServiceImp implements UserService {

	private static final Logger log=Logger.getLogger(UserServiceImp.class);
	@Autowired
	private UserMapper userMapper;
	public RespModel saveUser(RegisForm regisForm) {
		
		RespModel respModel =new RespModel();
		if(!regisForm.getPassword().equals(regisForm.getConfirmedPassword())){
			log.error(ResultMsg.PWD_CONFIRM_ERROR.getMsg());
			respModel.setResultCode(ResultCode.ERROR.getCode());
			respModel.setResultMsg(ResultMsg.PWD_CONFIRM_ERROR.getMsg());
			return respModel;
		}else{
			User user=new User();
			user.setUserUuid(UUID.randomUUID().toString().replace("-",""));
			user.setEmail(regisForm.getEmail());
			user.setName(regisForm.getName());
			user.setPassword( MD5Util.GetMD5Code(regisForm.getPassword()));
			user.setPhoneNum(regisForm.getPhoneNum());
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			user.setCreateTime(timestamp);
			user.setUpdateTime(timestamp);
			try {			
				userMapper.saveUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(ResultMsg.SAVE_USER_ERROR.getMsg());
				respModel.setResultCode(ResultCode.ERROR.getCode());
				respModel.setResultMsg(ResultMsg.SAVE_USER_ERROR.getMsg());
				return respModel;
			}
		}
		respModel.setResultCode(ResultCode.OK.getCode());
		respModel.setResultMsg(ResultMsg.SAVE_USER_SUCC.getMsg());
		log.info(ResultMsg.SAVE_USER_SUCC.getMsg());
		return respModel;
	}
	public RespModel userLogin(RegisForm regisForm,HttpServletRequest request) {
		
		log.info(regisForm.toString(regisForm));
		RespModel respModel = new RespModel();			
		List<User> list=userMapper.validateLogin(regisForm.getEmail(), MD5Util.GetMD5Code(regisForm.getPassword()));
		//log.info(list);
		if(list==null||1!=list.size()){
			log.info(list.size());
			respModel.setResultCode(ResultCode.ERROR.getCode());
			respModel.setResultMsg(ResultMsg.LOGIN_ERROR.getMsg());
		}else{
			log.info(ResultMsg.LOGIN_SUCC.getMsg());
			User currentUser=new User();
			currentUser.setName(list.get(0).getName());
			currentUser.setEmail(list.get(0).getEmail());
			currentUser.setPhoneNum(list.get(0).getPhoneNum());
			currentUser.setUserUuid(list.get(0).getUserUuid());
			currentUser.setCreateTime(list.get(0).getCreateTime());
			respModel.setResultCode(ResultCode.OK.getCode());
			respModel.setResultMsg(ResultMsg.LOGIN_SUCC.getMsg());
			respModel.setObjectData(currentUser);
			request.getSession().setMaxInactiveInterval(1200);
			request.getSession().setAttribute(SysConstant.CURRENT_USER,currentUser);
		}		
		return respModel;
	}
	public RespModel validateUserEmail(String email) {
		RespModel respModel =new RespModel();
		respModel.setResultCode(ResultCode.OK.getCode());
		try {
			List<User> list = userMapper.validateUserEmail(email);
			log.info(list.size());
			if(list.isEmpty()){			
				respModel.setResultCode(ResultCode.OK.getCode());			
			}else{
				respModel.setResultCode(ResultCode.ERROR.getCode());
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			respModel.setResultCode(ResultCode.ERROR.getCode());
		}
		return respModel;
	}
	public RespModel validateUserName(String name) {
		RespModel respModel =new RespModel();
		List<User> list = userMapper.validateUserName(name);
		try {
			log.info(list.size());
			if(list.isEmpty()){			
				respModel.setResultCode(ResultCode.OK.getCode());			
			}else{
				respModel.setResultCode(ResultCode.ERROR.getCode());
			}					
		} catch (Exception e) {
			e.printStackTrace();
			respModel.setResultCode(ResultCode.ERROR.getCode());
		}
		return respModel;
	}

}
