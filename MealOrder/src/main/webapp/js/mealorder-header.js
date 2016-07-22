function initPageHeader(type){
	switch(type){
	   case 0:	   
	       $("<a href='/MealOrder/user/toMain'>麦奥登首页</a>").appendTo($("#page-header"));
	       $("<a href='/MealOrder/user/toLogin'>登录</a>").appendTo($("#page-header"));
	       break;
	   case 1:
		   $("<a href='/MealOrder/user/toMain'>麦奥登首页</a>").appendTo($("#page-header"));
	       $("<a href='/MealOrder/shoppingCar/myShoppingCar'>我的订餐车</a>").appendTo($("#page-header"));
	       $("<a href='/MealOrder/order/myOrders'>我的订单</a>").appendTo($("#page-header"));
	       $("<a href='/MealOrder/user/userInfo'>我的资料</a>").appendTo($("#page-header"));
	       $("<a href='/MealOrder/user/logout'>退出</a>").appendTo($("#page-header"));		  
	       break;
	   default :
		   break;
	}
}