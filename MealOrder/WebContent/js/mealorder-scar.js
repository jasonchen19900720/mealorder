function recalculatePrice(){	
	var amount=0;
	var price=0;
	var totalPrice=0;	
	$("#table-scar").find("tr").each(function(index0,element0){  
		   
		   $(this).find("td").each(function(index1,element1){
			     switch(index1){
	    	        case 1:
	    	    	   price=parseInt($(this).text());     	    	  
	    		       break;
	    	        case 2:
	    	    	   amount=parseInt($(this).find("input").val());	    	    	  
	    		       break;
	    		    default:
	    		       break;
	    	     }		     			    		     
	       });  
		   totalPrice+=price*amount;
	 }); 
	 $("#p-tprice").text("总价:"+totalPrice+"元");
}
function scarToJsonArray(){
	 var data;	  
	 var array=new Array();
	 $("#table-scar").find("tr").each(function(index0,element0){  
		   $(this).find("td").each(function(index1,element1){ 
		    	switch(index1){
		    	    case 0:
		    	        data='{"goodsName"'+':'+'"'+$(this).find("a").text()+'"';
		    	        array.push(data);
		    	        break;
		    	    case 1:
		    		    data='"price"'+':'+'"'+$(this).text()+'"';
		    		    array.push(data);
		    		    break;
		    	    case 2:
		    	    	data='"amount"'+':'+'"'+$(this).find("input").val()+'"'; 
		    		    array.push(data);
		    		    break;
		    	    case 4:
		    	    	data='"attribution"'+':'+'"'+$(this).text()+'"'; 
		    		    array.push(data);
		    		    break;    
		    	    case 5:
		    	    	data='"imgSrc"'+':'+'"'+$(this).text()+'"}'; 
		    		    array.push(data);
		    		    break;    
		    	 }	  	  
		   });  
	  }); 
	 data='['+array.toString()+']';		     
	 return data;
}

function delSCarItem(obj){
	 if(confirm("你确定要移除？")){		 
		 var goodsName=$(obj).parent().siblings(".goodsName").find("a").text();
	     var sendData="goodsName="+goodsName;
		 var options = {
		          url: '/MealOrder/shoppingCar/rmSCarItem',
		          type: 'get',
		          dataType: 'text',
		          data: sendData,
		          success: function (data) {     	  
		             if (data.length >0){                
		                if(data.indexOf("success",0)>=0){	                	 
		                	 $(obj).parent().parent().remove();
		                	 recalculatePrice();
		                	 isSCarEmpty();               	
		                }else if(data.indexOf("error",0)>=0){
		                	alert("移除失败");
		                }else{
		                	alert("您还未登录,请先登录");
		             	    window.location.href="/MealOrder/user/toLogin";
		                }
		             }
		          }
		  };
		  $.ajax(options);    
	 }else{
	    return;	        	 
	 }	
}
function submitOrder(){   	
        var sendData="orderInfo="+scarToJsonArray()+"&"+$("#form-order").serialize();    
        var options = {
            url: '/MealOrder/order/submitOrder',
            type: 'post',
            dataType: 'text',
            data: sendData,
            success: function (data) {
                if (data.length>0){                
                    if(data.indexOf("success",0)>=0){
                    	 alert("提交完成");                   	 
                    	 clearSCar();
                    }else if(data.indexOf("error",0)>=0){
                    	alert("提交失败");
                    }else{
                    	alert("您还未登录,请先登录");
                 	    window.location.href="/MealOrder/user/toLogin";
                    }
                }  
            }
        };
        $.ajax(options);
}

function isSCarEmpty(){
	if(0==$("#table-scar").find("td").length){
	    $("#p-tprice").text("亲，您的购物车还是空的，赶紧行动吧!");
	    $("#a-toshop").text("立刻选购");
	    $("#a-toshop").attr("href", "/MealOrder/menu/mainMenu");
	    $("#form-order").parent().remove();
	    $("#table-scar").remove();
	}
}
function clearSCar(){
	 $("#p-tprice").text("亲，您的购物车还是空的，赶紧行动吧!");
	 $("#a-toshop").text("立刻选购");
	 $("#form-order").parent().remove();
	 $("#table-scar").remove();	
}
function recalculatePrice(){	
	var amount=0;
	var price=0;
	var totalPrice=0;	
	$("#table-scar").find("tr").each(function(index0,element0){  	   
		   $(this).find("td").each(function(index1,element1){
			     switch(index1){
	    	        case 1:
	    	    	   price=parseInt($(this).text());     	    	  
	    		       break;
	    	        case 2:
	    	    	   amount=parseInt($(this).find("input").val());	    	    	  
	    		       break;
	    		    default:
	    		       break;
	    	     }		     			    		     
	       });  
		   totalPrice+=price*amount;
	 }); 
	 if(totalPrice!=0){
		 $("#p-tprice").text("总价:"+totalPrice+"元");
	 }	 
}
function numAdd(obj){  	
	var amount = $(obj).siblings("input").val();  	
	$(obj).siblings("input").val(parseInt(amount)+1); 
    var totalPrice=$("#p-tprice").text();
    var price=$(obj).parent().siblings(".price").text();
    totalPrice=totalPrice.substring(totalPrice.indexOf(":")+1,totalPrice.indexOf("元"));     
    totalPrice = parseInt(totalPrice)+parseInt(price);
    $("#p-tprice").text("总价:"+totalPrice+"元");
}  
function numDec(obj){ 
	var amount = $(obj).siblings("input").val();  
    amount = parseInt(amount)-1;  
    if(amount>0){  
    	if(amount==1){
    		$(obj).css("color","black");
    		$(obj).css("border-color","#DDD");
        }
    	$(obj).siblings("input").val(amount);      
    	var totalPrice=$("#p-tprice").text();
    	var price=$(obj).parent().siblings(".price").text();
        totalPrice=totalPrice.substring(totalPrice.indexOf(":")+1,totalPrice.indexOf("元"));
        totalPrice = parseInt(totalPrice)-parseInt(price);
        $("#p-tprice").text("总价:"+totalPrice+"元");
    }  
}  
function setRed(obj){			
	var amount=$(obj).siblings("input").val();
	if(parseInt(amount)!=1||$(obj).text()!="-"){
		$(obj).css("color","red");
		$(obj).css("border-color","red");
	}		
}
function setBlack(obj){
	$(obj).css("color","black");
	$(obj).css("border-color","#DDD");
}