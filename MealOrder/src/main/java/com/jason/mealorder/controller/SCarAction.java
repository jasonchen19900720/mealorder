package com.jason.mealorder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.common.SysEnum.ResultMsg;
import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.service.SCarService;
import com.jason.mealorder.viewmodel.GoodsItem;

@Controller
@RequestMapping(value="/shoppingCar")
public class SCarAction {

	private static Logger log=Logger.getLogger(SCarAction.class);
	
	@Autowired
	private SCarService sCarService;
	
	//添加商品到购物车
	@ResponseBody
	@RequestMapping(value="/addGoods")
	public String addGoodsToSCar(GoodsItem item,HttpServletRequest req){
		log.info("数量："+item.getAmount());
		log.info("菜名："+item.getGoodsName());
		log.info("价格："+item.getPrice());
		log.info("归属："+item.getAttribution());
		RespModel respModel=sCarService.addGoods(item, req);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			log.error("添加购物车异常："+respModel.getResultMsg());							
			return "error";										
		}else{
			log.info("添加购物车成功");
			return "success";
		}		
	}
		
	//显示购物车商品列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/myShoppingCar")
	public String showSCarDetail(Model model,HttpServletRequest req){	
		log.info("进入加载购物车信息");			
		RespModel respModel = sCarService.showSCar(req);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			log.error("获取购物车信息失败："+respModel.getResultMsg());						
			model.addAttribute("resultMsg", "error");			
		}else{
			log.info("获取购物车信息成功");
			model.addAttribute("resultMsg", "error");
			if(ResultMsg.GET_SCAR_EMPTY.getMsg().equals(respModel.getResultMsg())){
				log.info("购物车没有物品");
				model.addAttribute("scarItems", "EMPTY");			
			}else{
				List<GoodsItem> dataList=(List<GoodsItem>)respModel.getListData();
				Integer totalPrice=(Integer)respModel.getNumberData();
				model.addAttribute("scarItems", dataList);
				model.addAttribute("totalPrice", totalPrice);
			}			
		}		
		return "order/myShoppingCar";
	}	
	@ResponseBody
	@RequestMapping(value="/rmSCarItem")
	public String delSCarItem(@RequestParam(value="goodsName",required =true) String goodsName,
			                                                 HttpServletRequest req){
		log.info("进入移除购物车项");
		RespModel respModel = sCarService.removeGoods(goodsName, req);
		if(ResultCode.ERROR.getCode().equals(respModel.getResultCode())){
			log.error("移除购物车项异常："+respModel.getResultMsg());							
			return "error";								
		}else{
			log.info("移除购物车项成功");
			return "success";
		}		
	}
	
	
}
