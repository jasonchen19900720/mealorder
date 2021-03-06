package com.jason.mealorder.common;

public class SysEnum {

	public enum ResultCode {
		
	    ERROR("-1"),OK("0");
	    
	    private String code;
	    
	    private ResultCode(String code){
	    	this.setCode(code);
	    }
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

	}
	
	public enum ResultMsg {
		
		PWD_CONFIRM_ERROR("the passwords are not same"),
		SAVE_USER_ERROR("save user information error"),
		SAVE_USER_SUCC("save user information success"),
		LOGIN_ERROR("login failed"),
		LOGIN_SUCC("login success"),
		LOGIN_TIMEOUT("login timeout"),
		
		MODIFY_AMOUNT_SUCC("modify item success"),
		MODIFY_AMOUNT_ERROR("modify item error"),
		
		
		
		ADD_GOODS_SUCC("add item success"),
		ADD_GOODS_ERROR("add item error"),
		
		DEL_GOODS_SUCC("remove item success"),
		DEL_GOODS_ERROR("remove item error"),
		
		GET_SCAR_EMPTY("shopping car is empty"),
		GET_SCAR_SUCC("get shopping car success"),
		GET_SCAR_ERROR("get shopping car error");
		
		private String msg;
		
		private ResultMsg(String msg){
			this.setMsg(msg);
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
    public enum OrderStatus {
		
	    WAITING("正在出餐"),DELIVERING("正在送餐"),
	    CANCELED("已经关闭"),DONE("交易成功"),COMMENTED("已经评论"),
	    EXPIRED("订单过期");
	    
	    private String status;
	    
	    private OrderStatus(String status){
	    	this.setStatus(status);
	    }
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

	}
	
	
	
	
	
	
}
