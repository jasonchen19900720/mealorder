function createJspFront(user){
	if(user=='null'){
		   var element=$("<a></a>").text("登录");
		   element.attr("href","/MealOrder/user/toLogin");
		   $("#span_welcome").append(element);
	}else{
		$("#span_welcome").text("欢迎您,"+"${user.name}");
		$("#a_mySCar").text("我的购物车");
		$("#a_mySCar").attr("href","/MealOrder/shoppingCar/myShoppingCar");
		$("#a_myInfo").text("我的资料");
		$("#a_myInfo").attr("href","/MealOrder/user/userInfo");
		$("#a_logout").text("退出");
		$("#a_logout").attr("href","/MealOrder/user/logoutr");
	}	
}

function createJspFront0(user){	 
	  var a=document.getElementById("span_welcome");
	  if(user=='null'){		  
		  var element=document.createElement("a");
		  element.setAttribute("href","/MealOrder/user/toLogin")
		  element.innerHTML='登录';
		  a.appendChild(element);			  
	  }else{	 
          a.innerHTML='欢迎您,'+'${user.name}';		  
		  a=document.getElementById("a_mySCar");
		  a.setAttribute("href","/MealOrder/shoppingCar/myShoppingCar")
		  a.innerHTML='我的购物车';		  		  
		  a=document.getElementById("a_myInfo");
		  a.setAttribute("href","/MealOrder/user/userInfo")
		  a.innerHTML='我的资料';		  
		  a=document.getElementById("a_logout");
		  a.setAttribute("href","/MealOrder/user/logout")
		  a.innerHTML='退出';		  
	  }
}
