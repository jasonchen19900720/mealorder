
function showCourse(dishName,imgSrc,price){	
	var img = document.getElementById("img");
	var src="/MealOrder/image/"+imgSrc;
	img.setAttribute("src", src);	
	document.getElementById("input_gName").value=dishName;
	document.getElementById("input_price").value=price;	
	document.getElementById("input_imgSrc").value=imgSrc;
}
function numAdd(){  
    var quantity = document.getElementById("input_amount").value;  
    var numAdd = parseInt(quantity)+1;   
    document.getElementById("input_amount").value=numAdd;
}  

function numDec(){  
    var quantity = document.getElementById("input_amount").value;  
    var numDec = parseInt(quantity)-1;  
    if(numDec>0){  
    	if(numDec==1){
    	   document.getElementById("span_dec").style.color="black";
    	   document.getElementById("span_dec").style.borderColor="#DDDDDD";
        }
        document.getElementById("input_amount").value=numDec;      
    }  
}  
function setColorRed(spanId){
	var quantity = document.getElementById("input_amount").value;
	var num = parseInt(quantity); 
	if(num!=1||spanId!="span_dec"){
		document.getElementById(spanId).style.color="red";
		document.getElementById(spanId).style.borderColor="red";
	}		
}

function setColorBlack(spanId){
	document.getElementById(spanId).style.color="black";
	document.getElementById(spanId).style.borderColor="#DDDDDD";
}
function setStyle(){
	$("span").siblings(".selected").attr("class","tab");
	$("#tab_comment").attr("class","selected");	
	$("ul.commentFold").attr("class","commentUnfold");
}
function getComments(){
	var dishName=$("#input_gName").val();
	var sendData="dishName="+dishName;
    var options = {
           url: '/MealOrder/order/comments',
           type: 'post', 
           dataType: 'json',
           data: sendData,
           contentType: "application/x-www-form-urlencoded; charset=utf-8",
           success: function (data) {
               if (data.length>0){
            	   $("li").remove("#comment li")
            	   $.each(data,function (index, comment){           		
            		  $("#comment").append("<li>"+
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
function showComments(){
	getComments();
	setStyle();
}

function addGoods() {   	
    var sendData=$("#form_addGoods").serialize();
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