<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<style type="text/css">
button{width:120px;height:25px;border-bottom:1px solid #666666;background:#2E8B57;font-size: 15px;color:white;}
#input_id3{width:60px;height:25px;border-bottom:1px solid #666666;background:#2E8B57;font-size: 15px;color:white;}
body{background-color:#FFE4E1}
input.input_cla0{border-style: none;background-color:inherit;font-size: 15px;font-family: cursive;}
a{color:blue;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script type="text/javascript">
window.onload=function(){
	  var user = '<%=session.getAttribute("user") %>';	  
	  if(user=='null'){
		  window.location.href="/MealOrder/user/toLogin";
	  }else{
		  $("#input_id0").val("${user.name}");
		  $("#input_id1").val("${user.phoneNum}");
		  $("#input_id2").val("${user.email}");
	  }
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
function enableModify(){
	  var input=document.getElementById("input_id0");
	  input.removeAttribute("readonly");
	  input.style.background="white";	  
	  input=document.getElementById("input_id1");
	  input.removeAttribute("readonly");
	  input.style.background="white";
	  document.getElementById("input_id3").style.display="block";
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
   <div  align="center">
      <h2>我的个人信息</h2>      
      <div>
          <p>
            <button onclick="enableModify()" class="class0">编辑信息</button>
            <a href="/MealOrder/user/toModifyPwd" class="class0">密码修改</a>
          </p>
          <form action="">
             <label>我的昵称：</label>
             <input id="input_id0" class="input_cla0" type="text" name="name" value="" readonly="readonly">
             <br/><br/>
             <label>我的电话：</label>
             <input id="input_id1" class="input_cla0" type="text" name="phoneNum" value="" readonly="readonly">
             <br/><br/>
             <label>我的邮箱：</label>
             <input id="input_id2" class="input_cla0" type="text" name="name" value="" readonly="readonly">
             <br/><br/>
             <input id="input_id3" type="submit" value="保存" style="display:none;"/>
         </form>
      </div>
   </div>
</div>
</body>
</html>