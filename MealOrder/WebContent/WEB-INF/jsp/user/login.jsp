<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<style type="text/css">
label.formText{width:160px;text-align:right;display: inline-block;}
input.formText{width: 240px;height: 25px;}
input.formButton{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size: 18px;} 
div.formContainer{ width:450px;height:400px;background:#F5F5DC;position:absolute; left:50%;top:50%;margin: -200px 0 0 -225px;}             
a{color:#FF7F50;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
#a_fgtPwd{color: gray;text-decoration: none;}
#a_fgtPwd:hover {color:orange;text-decoration: underline;}
#a_mainPage{color:black;text-decoration: none;}
#a_mainPage:hover {color:orange;text-decoration: underline;}
body{background:url(/MealOrder/image/bg1.jpg);background-size:cover;background-color:#4169E1;}
</style>
<script type="text/javascript">
window.onload=function(){
	var loginMsg='<%=request.getAttribute("loginMsg")%>';
	if(loginMsg=="login failed"){
		alert("用户名或密码错误");
	}	
}
</script>
</head>
<body>
<div style="border:0px solid black;">
   <div align="right" style="font-size: 15px;">
           <a id="a_mainPage" href="/MealOrder/user/toMain">麦奥登首页</a>                 
   </div>  
   <div>         
         <h1 style="color: #FF7F50;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: #FF7F50;margin: 0;padding: 0;">mealorder.com</h3>
   </div>
   <div class="formContainer">
     <h2 align="center">账户登录</h2>
     <div>  
       <form action="/MealOrder/user/login" method="post">
          <label class="formText">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
          <input class="formText" type="text" name="email" />
          <br/><br/>   
          <label class="formText">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
          <input class="formText" type="password" name="password" value=""/>
          <br/><br/> 
          <p align="center" style="margin: 0;padding: 0;font-size: 10px;"><a id="a_fgtPwd" href="">忘记密码</a></p>
          <label class="formText"></label>
          <input class="formButton" type="submit" value="登录"/>  
       </form>
       <br/>
       <div align="center">还没有麦奥登？<a href="/MealOrder/user/toRegister">免费注册</a></div>
    </div>  
  </div>
</div>
</body>
</html>