<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>湘菜-麦奥登分享美食</title>
<style type="text/css">
 div.box{width:200px; height:"160px";text-align:center; font-szie:20px;}
 div.box img {width:100%;height:100%}
 a{color:blue;text-decoration: none;}
 a:hover {color:red;text-decoration: underline;}
 td.menuClass{border: 1px solid #DDDDDD;}
 div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script type="text/javascript">
window.onload=function(){	
	  var user = '<%=session.getAttribute("user") %>';	  
	  createJspFront(user);	 
}
function createJspFront(user){
	if(user=='null'){
		var element=$("<a></a>").text("登录");
		element.attr("href","/MealOrder/user/toLogin");
		$("#span_welcome").append(element);
	}else{		
		$("#span_welcome").text("欢迎您,"+"${user.name}");
		$("#a_mySCar").text("我的订餐车");
		$("#a_mySCar").attr("href","/MealOrder/shoppingCar/myShoppingCar");
		$("#a_myOrders").text("我的订单");
		$("#a_myOrders").attr("href","/MealOrder/order/myOrders");
		$("#a_myInfo").text("我的资料");
		$("#a_myInfo").attr("href","/MealOrder/user/userInfo");
		$("#a_logout").text("退出");
		$("#a_logout").attr("href","/MealOrder/user/logout");
	}	
}
$(function(){
	$("div.logo").click(function(){
		window.location.href="/MealOrder/user/toMain";				
	});
});

</script>
</head>
<body>
<div>
     <div align="right" style="font-size: 15px;"> 
            <span id="span_welcome"></span>  
            <a id="a_mySCar"></a>  
            <a id="a_myOrders"></a>
            <a id="a_myInfo"></a> 
            <a id="a_logout"></a>
            <a id="a_myOrders"></a>
      </div>             
      <div class="logo">         
         <h1 style="color: #FF7F50;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: #FF7F50;margin: 0;padding: 0;">mealorder.com</h3>
      </div>
           <h2> 对不起今天不营业！</h2>
</div>
</body>
</html>