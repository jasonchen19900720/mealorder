<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<style type="text/css">
label.formText{width:135px;text-align:right;display: inline-block;}
input.formText{width: 240px;height: 25px;margin-top:30px;}
input.formButton{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size: 18px;} 
div.formContainer{ width:450px;height:400px;background:#F5F5DC;position:absolute; left:50%;top:50%;margin: -200px 0 0 -225px;}             
a{color:#FF7F50;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
#a-fpwd{color: gray;text-decoration: none;}
#a-fpwd:hover {color:orange;text-decoration: underline;}
#a-main{color: blue;text-decoration: none;}
#a-main:hover {color:black;text-decoration: underline;}
div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
div.prompt{border:0px solid black;margin-left: 144px;color: red;font-size: 10px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script type="text/javascript">
$(function(){
	var loginMsg='<%=request.getAttribute("loginMsg")%>';
	if(loginMsg=="login failed"){
		alert("用户名或密码错误");
	}else if(loginMsg=="login timeout"){
		alert("登陆超时，请重新登录");
	}	
});
$(function(){
	$("div.logo").click(function(){
		window.location.href="/MealOrder/user/toMain";				
	});
});

function validForm(obj){
	var emailRegexp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
	var name=$(obj).attr("name");	
	switch (name) {	
	case "email":
		var email=$(obj).val();		
		if(null==email||0==email.length){
			$("#prompt-for-email").text("email不能为空！");			
		}else if(!emailRegexp.test(email)){
			$("#prompt-for-email").text("输入email格式不正确！");			
		}else{
			$("#prompt-for-email").text("");
		}		
		break;
	case "password":
		var password=$(obj).val();
		if(null==password||0==password.length){
			$("#prompt-for-password").text("密码不能为空！");	
		}else if(password.length<8||password.length>20){
			$("#prompt-for-password").text("输入密码长度介于8~20个字符之间！");			
		}else{
			$("#prompt-for-password").text("");
		}
		break;				
	default:
		break;
	}	
}
</script>
</head>
<body style="background:url(/MealOrder/image/bg8.jpg);background-size:cover;">
<div style="border:0px solid black;">
   <div align="right" style="font-size: 15px;">
           <a id="a-main" href="/MealOrder/user/toMain">麦奥登首页</a>                 
   </div>  
   <div class="logo">         
         <h1 style="color: red;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: red;margin: 0;padding: 0;">mealorder.com</h3>
   </div>
   <div class="formContainer">
     <h2 align="center">账户登录</h2>
     <div>  
       <form action="/MealOrder/user/login" method="post">
       <div>
          <label class="formText">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
          <input class="formText" type="text" name="email" onblur="validForm(this)"/>
       </div> 
       <div id="prompt-for-email" class="prompt"></div>  
       <div>
          <label class="formText">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
          <input class="formText" type="password" name="password" onblur="validForm(this)"/>
       </div>
       <div id="prompt-for-password" class="prompt"></div>
        <div> 
          <div align="center" style="font-size: 10px;margin-top: 30px;"><a id="a-fpwd" href="">忘记密码</a></div>
          <label class="formText"></label>
          <input class="formButton" type="submit" value="登录"/>
       </div>  
       </form>
       <br/>
       <div align="center">还没有麦奥登？<a href="/MealOrder/user/toRegister">免费注册</a></div>
    </div>  
  </div>
</div>
</body>
</html>