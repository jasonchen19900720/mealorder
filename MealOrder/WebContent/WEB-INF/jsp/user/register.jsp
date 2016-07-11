<%--
  Created by Jason chen
  Date: 2016/5/21
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
a{color:blue;text-decoration: none;}
a:hover {color:orange;text-decoration: underline;}
label.formText{width:160px;text-align:right;display:inline-block;}
input.formText{width: 240px;height: 25px;}
button.formButton{margin-top:20px; width:120px;height:30px;border:1px solid #666666;background:#FF7F50;font-size:18px;} 
div.formContainer{ width:450px;height:400px;background:#F5F5DC;position:absolute; left:50%;top:50%;margin: -200px 0 0 -225px;}
</style>
<script src="/MealOrder/js/jquery-3.0.0.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $("#btn").click(function () {
        var options = {
            url: '/MealOrder/user/register',
            type: 'post',
            dataType: 'text',
            data: $("#reg").serialize(),
            success: function (data) {
                if (data.length > 0)
                    $("#responseText").text(data);
            }
        };
        $.ajax(options);
        return false;
    });
});

function regisReq(){
    var xmlhttp;   
    if(window.XMLHttpRequest){	 
       xmlhttp=new XMLHttpRequest();
    }else{
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function(){
       if (xmlhttp.readyState==4 && xmlhttp.status==200){
          if((xmlhttp.responseText).indexOf("success")>=0){
        	  window.location.href="/MealOrder/user/toLogin";
        	  alert("注册成功请登录");
          }else{
        	  window.location.reload();
        	  alert("注册失败请重新注册");
          }
       }
    }
    xmlhttp.open("POST","/MealOrder/user/register",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    var data0=$("#reg input").map(function(){
    	 return ($(this).attr("name")+'='+$(this).val());
    	}).get().join('&') ;
    /*
    var data1=$("reg").serialize();
    document.getElementById("test0").innerHTML=data1;
    document.getElementById("test1").innerHTML=data1;
    */
    xmlhttp.send(data0);
} 
</script>
</head>
<body  bgcolor="#BC8F8F">
<div>
    <div align="right" style="font-size: 15px;">
           <a href="/MealOrder/user/toMain">麦奥登首页</a>                 
    </div>   
    <div>         
         <h1 style="color: #FF7F50;margin: 0;padding: 0;">麦奥登网</h1>
         <h3 style="color: #FF7F50;margin: 0;padding: 0;">mealorder.com</h3>
    </div>  
    <div class="formContainer">
       <h2 align="center">请填写注册信息</h2>
       <form action="/MealOrder/user/register" method="post" id="reg">
         <label class="formText">名&nbsp;&nbsp;&nbsp;&nbsp;字：</label>
         <input class="formText" type="text" name="name"/><br/><br/>
         <label class="formText">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
         <input class="formText" type="password" name="password" value=""/><br/><br/>
         <label class="formText">密码确认：</label>
         <input class="formText" type="password" name="confirmedPassword" value=""/><br/><br/>
         <label class="formText">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
         <input class="formText" type="text" name="email"/><br/><br/>
         <label class="formText">手机号码：</label>
         <input class="formText" type="text" name="phoneNum"/><br/><br/>
         <!--<input type="submit" value="提交"/>-->
      </form>
      <label class="formText"></label>
      <button class="formButton" id="btnReg" onclick="regisReq()">注册</button>
      <p id="test0"></p>
      <p id="test1"></p>
    </div>
</div>
</body>
</html>