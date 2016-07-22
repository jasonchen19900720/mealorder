<%--
  Created by Jason chen
  Date: 2016/5/21
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>麦奥登</title>
<style type="text/css">
#page-header a{color:blue;text-decoration: none;}
#page-header a:hover {color:orange;text-decoration: underline;}
a.commonHref{color:black;text-decoration: none;cursor: pointer;}
a.commonHref:hover {color:orange;text-decoration: underline;}
</style>
</head>
<body bgcolor="#FFE4E1" style="background:url(/MealOrder/image/bg0.jpg);background-size:cover;">
<div> 
      <div id="page-header" align="right" style="font-size: 15px;">
           <a id="a-main" href="/MealOrder/user/toMain">麦奥登首页</a>                 
      </div>
      <div>
         <h1>Please Login or Register</h1>
         <div>        
           <p><a class="commonHref" href="/MealOrder/user/toLogin" >登录</a></p>
     
           <p><a class="commonHref" href="/MealOrder/user/toRegister">注册</a></p>
     
           <p><a class="commonHref" href="/MealOrder/user/toMain">我先看看</a></p>
         </div>
      </div>
</div>
</body>
</html>