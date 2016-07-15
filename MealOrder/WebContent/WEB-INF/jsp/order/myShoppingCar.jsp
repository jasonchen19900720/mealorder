<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
table,td,th{border:1px solid #98bf21;}
td{text-align:center; font-szie:20px;width: 100px;}
th{background-color: #a7c942;}
div.outerContainer{width:820px; height:300px; border:0px solid #0000FF;overflow: hidden;}
div.subContainer{float:left; width:410px; height:300px; border:0px solid #0000FF;}      
label.formText{width:160px;text-align:right;display: inline-block;}
input.formText{width: 240px;height: 25px;}
button{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size: 18px;}
#page-header a{color:blue;text-decoration: none;}
#page-header a:hover {color:orange;text-decoration: underline;}
#page-header span,a{margin-right:10px;}
a.commonHref{color:black;text-decoration: none;cursor: pointer;}
a.commonHref:hover {color:orange;text-decoration: underline;}
input.amount {width: 30px; height: 20px;text-align:center;}
span.addDec{display:inline-block; line-height:20px; width: 20px; height: 20px;text-align: center;
     background: white; cursor: pointer; border: 1px solid #DDD;-moz-user-select: none; 
     -webkit-user-select: none; -ms-user-select: none; -khtml-user-select: none; user-select: none;}
div.logo{cursor: pointer; -moz-user-select: none; -webkit-user-select: none; -ms-user-select: none; 
         -khtml-user-select: none; user-select: none;border: 0px solid black;width: 132px;}     
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script src="/MealOrder/js/mealorder-scar.js"></script>
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
<title>购物车</title>
</head>
<body>
<div>
   <div id="page-header" align="right" style="font-size: 15px;"></div>
   <div class="logo">         
         <h1 style="color: red;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: red;margin: 0;padding: 0;">mealorder.com</h3>
   </div>
   <h2>我的订餐车信息:</h2>
   <div class="outerContainer">
     <div class="subContainer">
        <div>        
           <table id="table-scar">
           <c:if test="${scarItems!='EMPTY' && fn:length(scarItems) > 0}">
                <tr>
                   <th>菜名</th>  <th>单价</th>  <th>数量</th>  <th>操作</th>
                </tr>
                <c:forEach var="item" items="${scarItems}">
                <tr>
                   <td class="goodsName">
                        <a class="commonHref" href="/MealOrder/menu/dishDetail/${item.attribution}/${item.imgSrc}/">${item.goodsName}</a>
                   </td>
                   <td class="price">${item.price}</td>
                   <td>
                        <span class="addDec" onmouseover="setRed(this)" onmouseout="setBlack(this)" onclick="numDec(this)">-</span><input class="amount" type="text" value="${item.amount}"/><span class="addDec" onmouseover="setRed(this)" onmouseout="setBlack(this)" onclick="numAdd(this)">+</span>
                   </td>
                   <td><a class="commonHref" onclick="delSCarItem(this)">移除</a></td>
                   <td style="display: none;">${item.attribution}</td>
                   <td style="display: none;">${item.imgSrc}</td>
                </tr>
                </c:forEach>
           </c:if>     
           </table> 
        </div>
        <div>
            <c:if test="${scarItems=='EMPTY' || fn:length(scarItems)== 0}">
              <p id="p-tprice" align="center" style="font-size: 20px;">亲，您的购物车还是空的，赶紧行动吧!</p> 
              <a id="a-toshop" class="commonHref" href="/MealOrder/menu/mainMenu" style="margin-left:50px;">立刻选购</a> 
            </c:if> 
            <c:if test="${scarItems!='EMPTY' && fn:length(scarItems) > 0}">
              <p id="p-tprice" align="right" style="font-size: 20px;">总价:${totalPrice}元</p> 
              <a id="a-toshop" class="commonHref" href="/MealOrder/menu/mainMenu" style="margin-left:50px;"> 再去逛逛</a> 
            </c:if> 
        </div>        
     </div>   
     <div class="subContainer">
         <div align="center">
           <c:if test="${scarItems!='EMPTY' && fn:length(scarItems) > 0}">
              <form action="" id="form-order">
                <label>送餐地址：</label>
                <input type="text" name="address" class="formText"/><br/><br/>
                <label>联系电话：</label>
                <input type="text" name="phoneNum" class="formText"/><br/><br/>
                <label>备注信息：</label>
                <textarea name="userNotes" style="width:240px;height:60px;display: inline-block;"></textarea>            
              </form>
              <div align="center"><button onclick="submitOrder()">提交订单</button></div>
          </c:if>
         </div>      
     </div> 
   </div>
</div>
</body>
</html>