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
 #page-header a{color:blue;text-decoration: none;}
 #page-header a:hover {color:orange;text-decoration: underline;}
 #page-header span,a{margin-right:10px;}
 td.menuClass{border: 1px solid #DDDDDD;}
 div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script src="/MealOrder/js/mealorder-header.js"></script>
<script type="text/javascript">
$(function(){	
	var user = '<%=session.getAttribute("user") %>';
	if(user=='null'){
		initPageHeader(0);
	}else{
		$("<span>欢迎您${user.name}</span>").appendTo($("#page-header"));
		initPageHeader(1);
	}	
});
$(function(){
	$("div.logo").click(function(){
		window.location.href="/MealOrder/user/toMain";				
	});
});
</script>
</head>
<body>
<div>
     <div id="page-header" align="right" style="font-size: 15px;"></div>             
      <div class="logo">         
         <h1 style="color: red;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: red;margin: 0;padding: 0;">mealorder.com</h3>
      </div>
           <h2> 对不起今天不营业！</h2>
</div>
</body>
</html>