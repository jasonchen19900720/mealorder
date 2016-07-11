<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>麦奥登-账户中心</title>
</head>
<style type="text/css">
label.formText{width:160px;text-align:right;display: inline-block;}
input.formText{width: 240px;height: 25px;}
input.formButton{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size: 18px;}            
div.formContainer{ width:450px;height:400px;background:#F5F5DC;position:absolute; left:50%;top:50%;margin: -200px 0 0 -225px;} 
a{color:blue;text-decoration: none;}
a:hover {color:red;text-decoration: underline;}
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
<body>
<div style="border:0px solid black;">
   <div align="right" style="font-size: 15px;">
           <a href="/MealOrder/user/toMain">麦奥登首页</a>
           <span id="span_welcome"></span>
           <a id="a_mySCar"></a>
           <a id="a_myOrders"></a>
           <a id="a_myInfo"></a> 
           <a id="a_logout"></a>          
   </div>  
   <div class="logo">         
         <h1 style="color: #FF7F50;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: #FF7F50;margin: 0;padding: 0;">mealorder.com</h3>
   </div>
   <div class="formContainer">
     <h2 align="center">修改密码</h2>
     <div>  
       <form action="" method="post" style="color: gray;">
           <label class="formText">当前登陆密码：</label>
           <input class="formText" type="password" name="oldPassword"/>
           <br/><br/>   
           <label class="formText">新的登录密码：</label>
           <input class="formText" type="password" name="newPassword"/>
           <br/><br/>    
           <label class="formText">确认新的登录密码：</label>
           <input class="formText" type="password" name="newPassword"/>   
           <label class="formText"></label>
           <input class="formButton" type="submit" value="确定"/>  
       </form>      
    </div>  
  </div>
</div>
</body>
</html>