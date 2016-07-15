package com.jason.mealorder.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.entity.User;


/**
 * @author Jason
 */
public class LoginInterceptor implements HandlerInterceptor {

	
    private static final Logger log = Logger.getLogger(LoginInterceptor.class);
    private static Set<String> interceptUrl = null;
    static{
    	interceptUrl=new HashSet<String>();
    	
    	interceptUrl.add("/shoppingCar/addGoods");
    	
    	interceptUrl.add("/shoppingCar/myShoppingCar");
    	
    	interceptUrl.add("/shoppingCar/mySCar");
    	
    	interceptUrl.add("/shoppingCar/rmSCarItem");
    	
    	interceptUrl.add("/user/userInfo");
    	
    	//interceptUrl.add("/user/toModifyPwd");
    	
    	interceptUrl.add("/order/toComment");
    	
    	interceptUrl.add("/order/addComment");
    	
    	interceptUrl.add("/order/myOrders");
    	
    	interceptUrl.add("/order/received");
   	   	
    }
    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    	//log.info("afterCompletion enter");

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
    	//log.info("postHandle enter");

    }

    
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handler) throws Exception { 
    	log.info("preHandle enter "+req.getRequestURI());
    	boolean interceptFlag=false;
    	String uri=req.getRequestURI();
    	for(String str:interceptUrl){  		
    		if(uri.contains(str)){
    			interceptFlag=true;
    		} 		
    	}
    	if(!interceptFlag){
    		return true;
    	}else{
    		User user=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
    		if(null==user){
    			log.info("未登录或登录超时");  			   			
    			System.out.println(req.getContextPath());
    			/*
    			OutputStream outputStream = resp.getOutputStream();    			
    			String data="loginMsg=login timeout";
    			resp.setHeader("content-type", "text/html;charset=UTF-8");   			
    			byte[] dataByte = data.getBytes("UTF-8");   			
    			outputStream.write(dataByte);
    			
    			resp.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器   			
    			PrintWriter out = resp.getWriter();//获取PrintWriter输出流    			    			
    			//out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
    			//等同于response.setHeader("content-type", "text/html;charset=UTF-8");    			
    			out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
    			out.write(data);
    			*/  	   			
    	        resp.sendRedirect("/MealOrder/user/toLogin");
    			return false;
    		}else{   			 
    			return true;
    		}    		   	    		
    	}      
    }
}
