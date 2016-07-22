package com.jason.mealorder.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumProducer {

	private static long orderNo =0;  
    private static String date=null; 
    //private static Lock lock = new ReentrantLock();
	public  static synchronized String getOrderNum() {  
	        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());  
	        if(date==null||!date.equals(str)){  
	            date = str;  	             
	        }            
	        long orderNum = Long.parseLong((date)) * 1000;  
	        //lock.lock();
	        orderNo ++;
	        orderNum += orderNo; 
	        //lock.unlock();
	        return String.valueOf(orderNum);  
    }  
	/*
	public static void main(String[] args){  
	       	          
		 String s=getOrderNum();
		 System.out.println(s);
	}  
	*/
}
