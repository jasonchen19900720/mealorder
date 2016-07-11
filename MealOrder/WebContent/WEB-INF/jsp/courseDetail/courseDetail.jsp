<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>麦奥登 - 菜品详情</title>
<style type="text/css">
div.divClass0 {width:800px; height:300px;overflow:hidden;border:0px solid red;} 
div.divClass1 {float:left; width:400px; height:300px; border:0px solid #0000FF;}
input.amount {width: 50px; height: 28px;text-align:center;}
span.addDec{display:inline-block; line-height:28px; width: 35px; height: 28px;text-align: center;
           background: white; cursor: pointer; border: 1px solid #DDDDDD;-moz-user-select: none; 
           -webkit-user-select: none; -ms-user-select: none; -khtml-user-select: none; user-select: none;}
button{margin-top:20px; margin-left:60px; width:120px;height:30px;
       border-bottom:1px solid #666666;background:pink;font-size: 18px;}
a{color:blue;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
span.tab{display:inline-block;width: 100px;height: 28px;line-height:28px;cursor: pointer;
         margin-right: 10px;background: white;text-align: center;border: 1px solid #DDD;}
span.selected{display:inline-block;width: 100px;height: 28px;line-height:28px;cursor: pointer;
         margin-right: 10px;background:  #DDD;text-align: center;border: 1px solid #DDD;color: red} 
ul.commentFold{display:none;margin: 0 auto;padding: 0;list-style: none;}
ul.commentUnfold{ display:block;margin: 0 auto;padding: 0;list-style: none;}
ul li{width:800px; height: 75px;margin-bottom: 10px;border: 1px solid #DDD;} 
ul li p span{margin-right: 10px;}
div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script src="/MealOrder/js/courseDetail.js"></script>
<script type="text/javascript">
window.onload=function(){
	 var dish='<%=request.getAttribute("dish")%>';
	 var user = '<%=session.getAttribute("user")%>';
	 showCourse("${dish.dishName}","${dish.imgSrc}","${dish.price}");
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
})
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
   <h2 style="border:0px solid red;">菜品详情</h2>
    <div class="divClass0">
     <div class="divClass1"><img id="img" width="360px" height="240px"/></div> 
     <div class="divClass1">
     <form id="form_addGoods" action="">
       <p>
         <label>菜品：</label>
         <input type="text" id="input_gName" name="goodsName" readOnly="readonly"style="border-style: none;font-size: 18px;"/> 
         <input type="text" id="input_imgSrc" name="imgSrc" readOnly="readonly" style="display: none;"/>
       </p>
       <p>
         <label>单价：</label>
         <input type="text" id="input_price" name="price" readOnly="readonly" style="border-style: none;font-size: 18px;"/> 
       </p>
       <p>
         <label>数量：</label>
         <span  id="span_dec" class="addDec" onclick="numDec()" onmouseover="setColorRed('span_dec')" onmouseout="setColorBlack('span_dec')">-</span> 
         <input id="input_amount" class="amount" type="text" name="amount" value="1"/> 
         <span  id="span_add" class="addDec" onclick="numAdd()" onmouseover="setColorRed('span_add')" onmouseout="setColorBlack('span_add')">+</span>
       </p>        
     </form>
     <p><button onclick="addGoods()">加入购物车</button></p>    
     <p id="test"></p>
     </div>
   </div>
   <div>
      <span class="tab" id="tab_comment" onclick="showComments()">累计评价</span> 
      <span class="tab" id="tab_dishdetail">美食详情</span>
      <ul id="comment" class="commentFold">               
      </ul>
   </div>
</div>
</body>
</html>