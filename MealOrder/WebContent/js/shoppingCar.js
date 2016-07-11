function recalculatePrice(){	
	var amount=0;
	var price=0;
	var totalPrice=0;	
	$("#table_sCar").find("tr").each(function(index0,element0){  
		   
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
		   //$("#test0").text("price="+price+",amount="+amount+",totalPrice="+totalPrice);
	 }); 
	 document.getElementById("p_tPrice").innerHTML="总价:"+totalPrice+"元";
}

function numAdd(spanId){  	
	var trId=spanId.substring(spanId.indexOf("_")+1);
	var amountId="amount_"+trId;
	var amount = document.getElementById(amountId).value;  	
    document.getElementById(amountId).value=parseInt(amount)+1; 
    var totalPrice=document.getElementById("p_tPrice").innerHTML;
    totalPrice=totalPrice.substring(totalPrice.indexOf(":")+1,totalPrice.indexOf("元"));     
    totalPrice = parseInt(totalPrice)+parseInt(document.getElementById("price_"+trId).innerHTML);
    document.getElementById("p_tPrice").innerHTML="总价:"+totalPrice+'元';
}  

function numDec(spanId){ 
	//document.getElementById("test").innerHTML=spanId;
	var trId=spanId.substring(spanId.indexOf("_")+1);
	var amountId="amount_"+trId;
	var amount = document.getElementById(amountId).value;  
    amount = parseInt(amount)-1;  
    if(amount>0){  
    	if(amount==1){
    	   document.getElementById(spanId).style.color="black";
    	   document.getElementById(spanId).style.borderColor="#DDDDDD";
        }
        document.getElementById(amountId).value=amount;      
        var totalPrice=document.getElementById("p_tPrice").innerHTML;
        totalPrice=totalPrice.substring(totalPrice.indexOf(":")+1,totalPrice.indexOf("元"));
        totalPrice = parseInt(totalPrice)-parseInt(document.getElementById("price_"+trId).innerHTML);
        document.getElementById("p_tPrice").innerHTML="总价:"+totalPrice+'元';
    }  
}  

function setColorRed(spanId){
	var trId=spanId.substring(spanId.indexOf("_")+1);
	var amount = document.getElementById("amount_"+trId).value;
	var amount = parseInt(amount); 
	if(amount!=1||spanId.substring(0,9)!="priorSpan"){
		document.getElementById(spanId).style.color="red";
		document.getElementById(spanId).style.borderColor="red";
	}
		
}

function setColorBlack(spanId){
	document.getElementById(spanId).style.color="black";
	document.getElementById(spanId).style.borderColor="#DDDDDD";
}

function jsonArrayToTwoDArray(jsonArray){
	//document.getElementById("test").innerHTML=jsonArray;
	var str=jsonArray;
	str=str.replace(/\s+/g,"");
	var itemsStr=str.split("},{");
	var field,begin,end,b;
	var a=new Array();
	for(var i=0;i<itemsStr.length;i++){
		b=new Array();
		str=itemsStr[i];
		begin=str.indexOf("goodsName",0)+'goodsName":"'.length;
		end=str.indexOf('"',begin);
		field=str.substring(begin,end);		
		b[0]=field;				
		begin=str.indexOf("price",0)+'price":'.length;
		if(0>str.indexOf(',',begin)){
			field=str.substring(begin);
		}else{
			end=str.indexOf(',',begin);
			field=str.substring(begin,end);
		}
		b[1]=field;		
		begin=str.indexOf("amount",0)+'amount":'.length;
		if(0>str.indexOf(',',begin)){
			field=str.substring(begin);
		}else{
			end=str.indexOf(',',begin);
			field=str.substring(begin,end);
		}
		b[2]=field;
		b[3]="移除";
		
		
		
		begin=str.indexOf("imgSrc",0)+'imgSrc":"'.length;
		end=str.indexOf('"',begin);
		field=str.substring(begin,end);
		b.push(field);//数组末尾存图片源	
		a[i]=b;
	}	
	return a;
}

function getJsonArraySize(jsonArray){
	var jsonstr=jsonArray;
	var index=0;
	var count=0;
	while(1){
		index=jsonstr.indexOf("}",0);		
		if(index>0){
			count++;
			jsonstr=jsonstr.substring(index+1);
		}else{
			break;
		}		
	}
	return count;
}

function createSCarTable(sCarInfo){	
	var tr,td,aa,element,trId;
	var totalPrice=0;
	var n=getJsonArraySize(sCarInfo);
	document.getElementById("a_toShop").setAttribute("href", "/MealOrder/order/showMenu");
	if(n<=0){
		$("#form_subOrder").parent().remove();
		document.getElementById("p_tPrice").innerHTML="亲，您的购物车还是空的，赶紧行动吧!";
		document.getElementById("a_toShop").innerHTML="立刻选购";		
		return;
	}else{
		document.getElementById("a_toShop").innerHTML="再去逛逛";		
	}
	var table=document.getElementById("table_sCar");
	var twoD=jsonArrayToTwoDArray(sCarInfo);
	tr = document.createElement("tr");
	var items=new Array("菜名","单价","数量","操作");
	for(var i=0;i<items.length;i++){
		td = document.createElement("th");
		td.innerHTML=items[i];
		tr.appendChild(td);
	}
	table.appendChild(tr);
	for(var i=0;i<n;i++){
		aa=twoD[i];
		totalPrice+=aa[1]*aa[2];
		tr = document.createElement("tr");
		trId="trId"+i;
		tr.setAttribute("id",trId );
	    for(var j=0;j<items.length+1;j++){
	    	if(j==0){
	    		td = document.createElement("td");
	    		element = document.createElement("a");
	    		element.setAttribute("id","goodsName_"+trId);
	    		element.setAttribute("class","commonHref");
	    		element.setAttribute("href","/MealOrder/order/menu/"+aa[aa.length-1]+"/");
	    		element.innerHTML=aa[j];	    		
	    		td.appendChild(element);
	    	}else if(j==1){   
	    		td = document.createElement("td");	    		
	    		td.setAttribute("id","price_"+trId);
				td.innerHTML=aa[j];    					    		
	    	}else if(j==2){
	    		td = document.createElement("td");	    		
	    		element = document.createElement("span");
	    		element.setAttribute("id","priorSpan_"+trId);    		
	    		element.setAttribute("class","addDec");
	    		element.innerHTML="-";
	    		element.onclick=function(){numDec(this.getAttribute("id"));};
	    		element.onmouseover=function(){setColorRed(this.getAttribute("id"));};
	    		element.onmouseout=function(){setColorBlack(this.getAttribute("id"));};	    		
	    		td.appendChild(element);
	    		element = document.createElement("input");
	    		element.setAttribute("type","text");
	    		element.setAttribute("id","amount_"+trId);
	    		element.setAttribute("class","amount");
	    		element.onchange=function(){recalculatePrice();};
	    		element.value=aa[j];    		
	    		td.appendChild(element);
	    		element = document.createElement("span");
	    		element.setAttribute("id","rearSpan_"+trId);	    		
	    		element.setAttribute("class","addDec");
	    		element.innerHTML="+";
	    		element.onclick=function(){numAdd(this.getAttribute("id"));};
	    		element.onmouseover=function(){setColorRed(this.getAttribute("id"));};
	    		element.onmouseout=function(){setColorBlack(this.getAttribute("id"));};
	    		td.appendChild(element);	    		
	    	}else if(j==3){
	    		td = document.createElement("td");
	    		element = document.createElement("a");
	    		element.setAttribute("id","delete_"+trId);
	    		element.setAttribute("class","commonHref");
	    		element.innerHTML=aa[j];
	    		element.onclick=function(){delSCarItem(this.getAttribute("id"));};
	    		td.appendChild(element);	
	    	}else{
	    		td = document.createElement("td");	
	    		td.style.display= "none";
				td.innerHTML=aa[j];	
	    	}
	    	tr.appendChild(td);
		}    
	    table.appendChild(tr);
	}
	document.getElementById("p_tPrice").innerHTML="总价:"+totalPrice+'元';
}

function sCartableToJsonArray(){
	 var data;	  
	 var array=new Array();
	 $("#table_sCar").find("tr").each(function(index0,element0){  
		   $(this).find("td").each(function(index1,element1){ 
		    	switch(index1){
		    	    case 0:
		    	        data='{"goodsName"'+':'+'"'+$(this).text()+'"';
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
		    	    	data='"imgSrc"'+':'+'"'+$(this).text()+'"}'; 
		    		    array.push(data);
		    		    break;    
		    	 }	  	  
		   });  
	  }); 
	 data='['+array.toString()+']';		     
	 return data;
}

function delSCarItem(deleteId){
	var tempId = deleteId.replace("delete","goodsName");//需要删除的物品名id
	var sendData="goodsName="+$("#"+tempId).text();
    var options = {
          url: '/MealOrder/shoppingCar/rmSCarItem',
          type: 'get',
          dataType: 'text',
          data: sendData,
          success: function (data) {     	  
             if (data.length >0){                
                if(data.indexOf("success",0)>=0){
                	 tempId = deleteId.substring(deleteId.indexOf("_")+1);//需要删除的tr id
                	 $("#"+tempId).remove();
                	 recalculatePrice();
                	 isSCarEmpty();
                	 alert("移除成功");
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
}

function submitOrder(){   	
        var sendData="orderInfo="+sCartableToJsonArray()+"&"+$("#form_subOrder").serialize();    
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
	if(0==$("#table_sCar").find("td").length){
	    $("#p_tPrice").text("亲，您的购物车还是空的，赶紧行动吧!");
	    $("#a_toShop").text("立刻选购");
	    $("#a_toShop").attr("href", "/MealOrder/order/showMenu");
	    $("#form_subOrder").parent().remove();
	    $("#table_sCar").find("tr").remove();
	}
}
function clearSCar(){
	 $("#p_tPrice").text("亲，您的购物车还是空的，赶紧行动吧!");
	 $("#a_toShop").text("立刻选购");
	 $("#form_subOrder").parent().remove();
	 $("#table_sCar").find("tr").remove();	
}
function createElement(){
	 $('<input />', {
	        id: 'cbx',
	        name: 'cbx',
	        type: 'checkbox',
	        checked: 'checked',
	        click: function() {
	          alert("点我了~~");
	        }
	      }).appendTo($('#wrap'));	
}
