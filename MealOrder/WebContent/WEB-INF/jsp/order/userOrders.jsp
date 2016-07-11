<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<style type="text/css">
a{color:blue;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
ul {margin: 0 auto;padding: 0;list-style: none;}
ul li{width:1200px; height: 75px;margin-bottom: 10px;border: 1px solid #DDD;} 
ul li span{margin-right: 10px;}
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
    <div>
        <h2>我的订单:</h2>
        <ul id="orders" class="orders"> 
        <c:forEach var="item" items="${orders}">
           <li>
               <p><span>${item.submitTime}</span><span>订单号:${item.orderId}</span></p>
               <p> 
                  <c:forEach var="goodsItem" items="${item.orderInfoList}">
                      <span><a href="/MealOrder/order/menu/${goodsItem.imgSrc}/">${goodsItem.goodsName}</a>${goodsItem.amount}份</span>
                      <span>单价：${goodsItem.price}元</span> 
                  </c:forEach>
                  <span><strong>总价：${item.totalPrice}</strong></span>
                  <span><strong>${item.orderStatus}</strong></span>
                  <c:choose>
                    <c:when test="${item.orderStatus=='交易成功'}">
                       <span><strong><a href="/MealOrder/order/toComment?orderId=${item.orderId}">我要去评价</a></strong></span>
                    </c:when>
                    <c:when test="${item.orderStatus=='正在出餐'}">
                       <span><strong><a href="/MealOrder/order/received?orderId=${item.orderId}">确认收到订餐</a></strong></span>
                    </c:when>
                  </c:choose>                
               </p>
           </li>
        </c:forEach>              
        </ul>
    </div>
</div>
</body>
</html>