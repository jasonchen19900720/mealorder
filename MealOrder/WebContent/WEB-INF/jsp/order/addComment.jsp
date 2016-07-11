<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价美食-麦奥登</title>
<style type="text/css">
a{color:blue;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
ul {margin: 0 auto;padding: 0;list-style: none;}
ul li{width:1200px;margin-bottom: 30px;border: 1px solid #DDD;}
ul li td{width:200px;font-szie:20px; text-align:center;}
ul li td img {width:200px;height:120px} 
textarea{width:600px;height:120px;display: inline-block;}
td.menuClass{border: 1px solid #DDDDDD;}
button{width: 120px;height: 30px;font-size:18px;}
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
	  $("td.menuClass").mouseover(function(){
		  $(this).css("border-color","red");
	  });
});
$(function(){
	  $("td.menuClass").mouseout(function(){		 
		  $(this).css("border-color","#DDDDDD");		 
	  });
});
$(function(){
	$("div.logo").click(function(){
		window.location.href="/MealOrder/user/toMain";				
	});
});
function CommentsToJsonArray(){
	 var data;	  
	 var array=new Array();
	 $("table.comments").find("tr").each(function(index0,element0){  
		   $(this).find("td").each(function(index1,element1){ 
		    	switch(index1){
		    	    case 0:
		    	        data='{"dishName"'+':'+'"'+$(this).text()+'"';
		    	        array.push(data);
		    	        break;
		    	    case 1:
		    		    data='"content"'+':'+'"'+$(this).find("textarea").val()+'"}';
		    		    array.push(data);
		    		    break;		    	  
		    	 }	  	  
		   });  
	  }); 
	 data='['+array.toString()+']';		     
	 return data;
}
function addComment() {  	 
    window.location.href="/MealOrder/order/addComment?"+
    		        "comments="+CommentsToJsonArray()+"&orderId="+$("div h3 span").text();
}
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
       <h3>美食评论-订单号：<span>${orderModel.orderId}</span></h3>
       <ul>
          <c:forEach var="goodsItem" items="${orderModel.orderInfoList}">
             <li>  
                <table class="comments">  
                <tr>        
                   <td class="menuClass">                     
                       <a href="/MealOrder/order/menu/${goodsItem.imgSrc}/">
                             <img src="/MealOrder/image/${goodsItem.imgSrc}">${goodsItem.goodsName}
                       </a>                                        
                   </td>
                   <td>                                                                                                                                       
                       <textarea></textarea>                                                         
                   </td>                                  
                   </tr> 
                </table>           
             </li>         
          </c:forEach>      
       </ul>
       <div align="center">
           <button onclick="addComment()">发表评论</button>
           <p id="test"></p>
       </div>
    </div>   
</div>
</body>
</html>