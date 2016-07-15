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
#page-header a{color:blue;text-decoration: none;}
#page-header a:hover {color:orange;text-decoration: underline;}
#page-header span,a{margin-right:10px;}
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
input.hiddenBorder{border-style: none;font-size: 18px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script src="/MealOrder/js/mealorder-dishdetail.js"></script>
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
	var dish='<%=request.getAttribute("dish")%>';
	showCourse("${dish.dishName}","${dish.imgSrc}","${dish.attribution}","${dish.price}");
});

$(function(){
	$("#span-dec").click(function(){numDec();});
	$("#span-dec").mouseover(function(){setColorRed('span-dec');});
	$("#span-dec").mouseout(function(){setColorBlack('span-dec');});
	$("#span-add").click(function(){numAdd();});
	$("#span-add").mouseover(function(){setColorRed('span-add');});
	$("#span-add").mouseout(function(){setColorBlack('span-add');});
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
   <h2 style="border:0px solid red;">菜品详情</h2>
    <div class="divClass0">
     <div class="divClass1"><img id="img" width="360px" height="240px"/></div> 
     <div class="divClass1">
     <form id="form-add-goods" action="">
       <p>
         <label>菜品：</label>
         <input class="hiddenBorder" type="text" id="input-goods-name" name="goodsName" readOnly="readonly"/> 
         <input class="hiddenBorder" type="text" id="input-attribution" name="attribution" readOnly="readonly" style="display: none;"/>
         <input class="hiddenBorder" type="text" id="input-img-src" name="imgSrc" readOnly="readonly" style="display: none;"/>
       </p>
       <p>
         <label>单价：</label>
         <input class="hiddenBorder" type="text" id="input-price" name="price" readOnly="readonly" /> 
       </p>
       <p>
         <label>数量：</label>
         <span  id="span-dec" class="addDec">-</span> 
         <input id="input-amount" class="amount" type="text" name="amount" value="1"/> 
         <span  id="span-add" class="addDec">+</span>
       </p>        
     </form>
     <p><button onclick="addGoods()">加入购物车</button></p>    
     <p id="test"></p>
     </div>
   </div>
   <div>
      <span class="tab" id="tab-comment" onclick="showComments()">累计评价</span> 
      <span class="tab" id="tab-dish-detail" onclick="showIntroduction()">美食详情</span>
      <ul id="ul-share" class="commentFold"></ul>
   </div>
</div>
</body>
</html>