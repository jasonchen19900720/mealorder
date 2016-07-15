function regisReq0(){  
    var data0=$("#reg input").map(function(){
    	 return ($(this).attr("name")+'='+$(this).val());
    	}).get().join('&') ;      
} 
function regisReq(){
	    if(!validForReg()){
	    	return;
	    }
    	var sendData=$("#form-reg").serialize();
        var options = {
            url: '/MealOrder/user/register',
            type: 'post',
            dataType: 'text',            
            data: sendData,
            success: function (data) {
                if (data.indexOf("success")>=0){
                	window.location.href="/MealOrder/user/toLogin";
              	    alert("注册成功请登录");
                }else{
                	window.location.reload();
              	    alert("注册失败请重新注册");
                }                    
            }
        };
        $.ajax(options);       
}
function validForReg(){
	var emailRegexp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
	var phoneNumRegexp = /^[1][3,4,5,8][0-9]{9}$/;	
	var result=true;
	switch ("name") {
	case "name":
		var name=$("input[name=name]").val();		
		if(null==name||0==name.length){
			$("#prompt-for-name").text("名字不能为空！");
			result=false;
		}else if(name.length<1||name.length>10){
			$("#prompt-for-name").text("输入名字长度介于1~10个字符之间！");
			result=false;
		}else{
			$("#prompt-for-name").text("");			
		}		
	case "email":
		var email=$("input[name=email]").val();		
		if(null==email||0==email.length){
			$("#prompt-for-email").text("email不能为空！");
			result=false;
		}else if(!emailRegexp.test(email)){
			$("#prompt-for-email").text("输入email格式不正确！");
			result=false;
		}else{
			$("#prompt-for-email").text("");
		}				
	case "password":
		var password=$("input[name=password]").val();
		if(null==password||0==password.length){
			$("#prompt-for-password").text("密码不能为空！");
			result=false;
		}else if(password.length<8||password.length>20){
			$("#prompt-for-password").text("输入密码长度介于8~20个字符之间！");
			result=false;
		}else{
			$("#prompt-for-password").text("");
		}		
	case "confirmedPassword":
		var password=$("input[name=password]").val();
		var rpassword=$("input[name=confirmedPassword]").val();
		if(null==rpassword||0==rpassword.length){
			$("#prompt-for-rpassword").text("确认密码不能为空！");	
			result=false;
		}else if(rpassword.length<8||rpassword.length>20){
			$("#prompt-for-rpassword").text("确认密码长度介于8~20个字符之间！");
			result=false;
		}else if(password.length>=8 && 20>=password.length && password!=rpassword ){
			$("#prompt-for-rpassword").text("两次输入密码不一致！");
			result=false;
		}else{
			$("#prompt-for-rpassword").text("");
		}		
	case "phoneNum":
		var phoneNum=$("input[name=phoneNum]").val();;
		if(null==phoneNum||""==phoneNum){
			$("#prompt-for-phone").text("手机号码不能为空！");
			result=false;
		}else if(!phoneNumRegexp.test(phoneNum)){
			$("#prompt-for-phone").text("手机号码格式不正确！");
			result=false;
		}else{
			$("#prompt-for-phone").text("");
		}		
	default:
		break;
	}	
	return result;	
}
function getValidMsg(obj){
	var emailValid='<%=request.getAttribute("emailValid")%>';
	var name=$(obj).attr("name");
	switch (name) {
	case "email":
		$("#prompt-for-email").text(emailValid);
		break;

	default:
		break;
	}	
}
function validForm(obj){
	var emailRegexp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
	var phoneNumRegexp = /^[1][3,4,5,8][0-9]{9}$/;
	var name=$(obj).attr("name");	
	switch (name) {
	case "name":
		var name=$(obj).val();
		if(null==name||0==name.length){
			$("#prompt-for-name").text("名字不能为空！");	
		}else if(name.length<1||name.length>10){
			$("#prompt-for-name").text("输入名字长度介于1~10个字符之间！");			
		}else{
			validName();			
		}
		break;
	case "email":
		var email=$(obj).val();		
		if(null==email||0==email.length){
			$("#prompt-for-email").text("email不能为空！");			
		}else if(!emailRegexp.test(email)){
			$("#prompt-for-email").text("输入email格式不正确！");			
		}else{
			validEmail();
		}		
		break;
	case "password":
		var password=$(obj).val();
		if(null==password||0==password.length){
			$("#prompt-for-password").text("密码不能为空！");	
		}else if(password.length<8||password.length>20){
			$("#prompt-for-password").text("输入密码长度介于8~20个字符之间！");			
		}else{
			$("#prompt-for-password").text("");
		}
		break;
	case "confirmedPassword":
		var password=$("input[name=password]").val();
		var rpassword=$(obj).val();
		if(null==rpassword||0==rpassword.length){
			$("#prompt-for-rpassword").text("确认密码不能为空！");	
		}else if(rpassword.length<8||rpassword.length>20){
			$("#prompt-for-rpassword").text("确认密码长度介于8~20个字符之间！");			
		}else if(password.length>=8 && 20>=password.length && password!=rpassword ){
			$("#prompt-for-rpassword").text("两次输入密码不一致！");
		}else{
			$("#prompt-for-rpassword").text("");
		}
		break;
	case "phoneNum":
		var phoneNum=$(obj).val();
		if(null==phoneNum||""==phoneNum){
			$("#prompt-for-phone").text("手机号码不能为空！");
		}else if(!phoneNumRegexp.test(phoneNum)){
			$("#prompt-for-phone").text("手机号码格式不正确！");			
		}else{
			$("#prompt-for-phone").text("");
		}
		break;
	default:
		break;
	}	
}
function validName(){	     
	     var sendData="name="+$("input[name=name]").val();	     
		 var options = {
		          url: '/MealOrder/user/validateName',
		          type: 'get',
		          dataType: 'json',
		          data: sendData,
		          success: function (data) {
		             if (!data.valid){                
		            	$("#prompt-for-name").text("该昵称已经被其他用户注册！");		            	
		             }else{
		            	$("#prompt-for-name").text(""); 		            	
		             }		             
		          },
		          error:function(){
		        	  $("#prompt-for-name").text("校验发生异常"); 
		          }
		  };
		  $.ajax(options);  		  
}
function validEmail(){		 
     var sendData="email="+$("input[name=email]").val();
	 var options = {
	          url: '/MealOrder/user/validateEmail',
	          type: 'get',
	          dataType: 'json',
	          data: sendData,
	          success: function (data) {	        	 
	             if (!data.valid){                
	            	$("#prompt-for-email").text("该邮箱已经被其他用户注册！");	            	
	             }else{
	            	$("#prompt-for-email").text(""); 	            	
	             }		             
	          },
	          error:function(){
	        	  $("#prompt-for-email").text("校验发生异常"); 
	          }
	  };
	  $.ajax(options); 	  
}