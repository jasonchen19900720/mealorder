<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jason</title>
<style type="text/css">
#menu ul{margin: 0 auto;padding: 0;list-style: none;}
#menu ul li{background: #EEE;float: right;width:130px; height: 20px;line-height: 20px;text-align: left;margin-right: 10px; border:1px solid #0000FF;font-size: 15px;}
#menu ul li a{color:#000;text-decoration: none;display: block;}
#menu ul li a:hover{color:orange;text-decoration: underline;}
#menu ul li	ul li{float: none;width:130px;margin: 0;} 
#menu ul li ul{ display:none;  width:130px;}
#menu ul li.listshow ul{ display:block;} 
}
</style>
<script type="text/javascript">
function showMenu(){
	var eles= document.getElementById("menu").getElementsByTagName("li");
	for(var i=0;i<eles.length;i++){
		eles[i].onmouseover=function(){
		     //this.className +=this.className.length>0?" ":""+"listshow";	
			this.className ="listshow";
		}
		eles[i].onmouseout=function(){
		     this.className =this.className.replace("listshow","");	
		}
	} 	 
}
window.onload=function(){	
	showMenu();
}
</script>
</head>
<body>
<div id="menu" align="right">
   <ul>
     <li><a href="">欢迎您,jasonchen</a>
       <ul>
         <li><a href="">我的信息</a></li>
         <li><a href="">我的订单</a></li>
         <li><a href="">我的购物车</a></li>  
         <li><a href="">退出</a></li>     
       </ul>
     </li>
     <li><a href="">C#</a>
       <ul>
         <li><a href="">二级菜单</a></li>
         <li><a href="">二级菜单</a></li>
         <li><a href="">二级菜单</a></li>       
       </ul>
     </li>
     <li><a href="">PHP</a>
       <ul>
         <li><a href="">二级菜单</a></li>
         <li><a href="">二级菜单</a></li>
         <li><a href="">二级菜单</a></li>       
       </ul>
     </li>
   </ul>
</div>
</body>
</html>