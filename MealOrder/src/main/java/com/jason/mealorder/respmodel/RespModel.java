package com.jason.mealorder.respmodel;

import java.util.List;


public class RespModel {

	private String resultCode;
	private String resultMsg;
	private Integer numberData;
	private String stringData;
	private String jsonStrData;
	private List<?> listData;
	private Object objectData;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public Object getObjectData() {
		return objectData;
	}
	public void setObjectData(Object objectData) {
		this.objectData = objectData;
	}
	public String getStringData() {
		return stringData;
	}
	public void setStringData(String stringData) {
		this.stringData = stringData;
	}
	public List<?> getListData() {
		return listData;
	}
	public void setListData(List<?> listData) {
		this.listData = listData;
	}
	public Integer getNumberData() {
		return numberData;
	}
	public void setNumberData(Integer numberData) {
		this.numberData = numberData;
	}
	public String getJsonStrData() {
		return jsonStrData;
	}
	public void setJsonStrData(String jsonStrData) {
		this.jsonStrData = jsonStrData;
	}
}
