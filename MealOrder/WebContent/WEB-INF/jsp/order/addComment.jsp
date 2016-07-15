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
#page-header a{color:blue;text-decoration: none;}
#page-header a:hover {color:orange;text-decoration: underline;}
#page-header span,a{margin-right:10px;}
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
$(function(){
	  $("td.menuClass").mouseover(function(){$(this).css("border-color","red");});
	  $("td.menuClass").mouseout(function(){$(this).css("border-color","#DDDDDD");});
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
    window.location.href="/MealOrder/comment/addComment?"+
    		        "comments="+CommentsToJsonArray()+"&orderId="+$("div h3 span").text();
}
</script>
</head>
<body>
<div>
    <div id="page-header" align="right" style="font-size: 15px;"></div> 
    <div class="logo">         
        <h1 style="color: red;margin: 0;padding: 0;">麦奥登网</h1>
        <h3 style="color: red;margin: 0;padding: 0;">mealorder.com</h3>
    </div>
    <div>
       <h3>美食评论-订单号：<span>${orderModel.orderId}</span></h3>
       <ul>
          <c:forEach var="goodsItem" items="${orderModel.orderInfoList}">
             <li>  
                <table class="comments">  
                <tr>        
                   <td class="menuClass">                     
                       <a href="/MealOrder/order/menu/dishDetail/${goodsItem.imgSrc}/">
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