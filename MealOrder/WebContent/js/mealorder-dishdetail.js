
function showCourse(dishName,imgSrc,attribution,price){	
	var img = document.getElementById("img");
	var src="/MealOrder/image/"+imgSrc;
	img.setAttribute("src", src);	
	document.getElementById("input-goods-name").value=dishName;
	document.getElementById("input-img-src").value=imgSrc;	
	document.getElementById("input-attribution").value=attribution;
	document.getElementById("input-price").value=price;	
}
function numAdd(){  
    var quantity = document.getElementById("input-amount").value;  
    var numAdd = parseInt(quantity)+1;   
    document.getElementById("input-amount").value=numAdd;
}  

function numDec(){  
    var quantity = document.getElementById("input-amount").value;  
    var numDec = parseInt(quantity)-1;  
    if(numDec>0){  
    	if(numDec==1){
    	   document.getElementById("span-dec").style.color="black";
    	   document.getElementById("span-dec").style.borderColor="#DDDDDD";
        }
        document.getElementById("input-amount").value=numDec;      
    }  
}  
function setColorRed(spanId){
	var quantity = document.getElementById("input-amount").value;
	var num = parseInt(quantity); 
	if(num!=1||spanId!="span-dec"){
		document.getElementById(spanId).style.color="red";
		document.getElementById(spanId).style.borderColor="red";
	}		
}

function setColorBlack(spanId){
	document.getElementById(spanId).style.color="black";
	document.getElementById(spanId).style.borderColor="#DDDDDD";
}

function getComments(){
	var dishName=$("#input-goods-name").val();
	var sendData="dishName="+dishName;
    var options = {
           url: '/MealOrder/comment/showComments',
           type: 'post', 
           dataType: 'json',
           data: sendData,
           contentType: "application/x-www-form-urlencoded; charset=utf-8",
           success: function (data) {
               if (data.length>0){
            	   $("li").remove("#ul-share li")
            	   $.each(data,function (index, comment){           		
            		  $("#ul-share").append("<li>"+
            				                      "<p>"+
            				                             "<span>"+comment.username+"</span>"+
            				                             "<span>"+comment.addTime+"</span>"+
            				                      "</p>"+
            				                      "<p>"+comment.content+"</p>"+
            				               "</li>");
            	   });
               }                  
           }         
    };
    $.ajax(options);	
}
function getIntroduction(){
	$("li").remove("#ul-share li");
	$("#ul-share").append("<li>"+"<p>"+"抱歉,暂时没有详细介绍"+"</p>"+"</li>");
}
function setStyle(jqueryObj){
	$("span").siblings(".selected").attr("class","tab");
	jqueryObj.attr("class","selected");	
	$("ul.commentFold").attr("class","commentUnfold");
}
function showComments(){
	getComments();	
	setStyle($("#tab-comment"));
}
function showIntroduction(){
	getIntroduction();
	setStyle($("#tab-dish-detail"));
}
function addGoods() {   	
    var sendData=$("#form-add-goods").serialize();
    var options = {
           url: '/MealOrder/shoppingCar/addGoods',
           type: 'post',          
           dataType: 'text',
           data: sendData,
           success: function (data) {
        	   //alert(data);
               if (data.length > 0){
               	   if(data.indexOf("success",0)>=0){
                  	   alert("添加成功");
                   }else if(data.indexOf("error",0)>=0){
                	   alert("添加失败");
                   }else{
                	   alert("您还未登录,请先登录");
                	   window.location.href="/MealOrder/user/toLogin";
                   }
               }                  
           }           
    };
    $.ajax(options);
}