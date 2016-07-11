<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
table,td,th{border:1px solid #98bf21;}
td{text-align:center; font-szie:20px;width: 100px;}
th{background-color: #a7c942;}
div.outerContainer{width:820px; height:300px; border:0px solid #0000FF;overflow: hidden;}
div.childContainer{float:left; width:410px; height:300px; border:0px solid #0000FF;}      
label.formText{width:160px;text-align:right;display: inline-block;}
input.formText{width: 240px;height: 25px;}
button{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size: 18px;}
a{color:blue;text-decoration: none;cursor: pointer;}
a.commonHref{color:black;text-decoration: none;cursor: pointer;}
a:hover {color:orange;text-decoration: underline;}
input.amount {width: 30px; height: 20px;text-align:center;}
span.addDec{display:inline-block; line-height:20px; width: 20px; height: 20px;text-align: center;
     background: white; cursor: pointer; border: 1px solid #DDDDDD;-moz-user-select: none; 
     -webkit-user-select: none; -ms-user-select: none; -khtml-user-select: none; user-select: none;}
div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}     
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script src="/MealOrder/js/shoppingCar.js"></script>
<script type="text/javascript">
window.onload=function(){
	  var sCarInfo='<%=request.getAttribute("sCarInfo")%>';	  
	  var user = '<%=session.getAttribute("user") %>';	  
	  if(user=='null'){		  
		  var element=$("<a></a>").text("登录");
		  element.attr("href","/MealOrder/user/toLogin");
		  $("#span_welcome").append(element); 
	  }else{	 
		  $("#span_welcome").text("欢迎您,"+"${user.name}");
		  createSCarTable(sCarInfo);
	  }
}
$(function(){
	$("div.logo").click(function(){
		window.location.href="/MealOrder/user/toMain";				
	});
});
</script>
<title>我的购物车</title>
</head>
<body>
<div>
   <div align="right" style="font-size: 15px;">
       <a href="/MealOrder/user/toMain">麦奥登首页</a>
       <span id="span_welcome"></span>     
   </div>
   <div class="logo">         
         <h1 style="color: #FF7F50;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: #FF7F50;margin: 0;padding: 0;">mealorder.com</h3>
   </div>
   <h2>我的订餐车信息:</h2>
   <div class="outerContainer">
     <div class="childContainer">
        <table id="table_sCar"></table>  
        <p id="p_tPrice" align="center" style="font-size: 20px;"></p> 
        <a id="a_toShop" style="margin-left: 50px;"></a>  
     </div>   
     <div class="childContainer">
         <div align="center">
            <br/>
            <form action="" id="form_subOrder">
              <label>送餐地址：</label>
              <input type="text" name="address" class="formText"/><br/><br/>
              <label>联系电话：</label>
              <input type="text" name="phoneNum" class="formText"/><br/><br/>
              <label>备注信息：</label>
              <textarea name="userNotes" style="width:240px;height:60px;display: inline-block;"></textarea>            
            </form>
            <div align="center"><button onclick="submitOrder()">提交订单</button></div>
         </div>
     </div> 
   </div>
   <%--
   <div align="center" style="border:1px solid #000000;">
      <p><button id="butt_test" onclick="">测试</button></p>
      <p id="test"></p>    
   </div> 
    --%>   
</div>
</body>
</html>