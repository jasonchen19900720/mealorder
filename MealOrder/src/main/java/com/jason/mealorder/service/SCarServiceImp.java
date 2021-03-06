package com.jason.mealorder.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.common.SysEnum.ResultMsg;
import com.jason.mealorder.entity.SCarItem;
import com.jason.mealorder.entity.User;
import com.jason.mealorder.mapper.SCarItemMapper;
import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.viewmodel.GoodsItem;

@Service
public class SCarServiceImp implements SCarService {

	private static Logger log = Logger.getLogger(SCarServiceImp.class);
	@Autowired
	private SCarItemMapper sCarItemMapper;	
	public RespModel addGoods(GoodsItem goodsItem,HttpServletRequest req) {	
		log.info("addGoods enter");	
		RespModel respModel =new RespModel();
		/*
		String cookieValue=JsonUtil.objectToJsonStr(goodsItem);
		String cookieName=goodsItem.getGoodsName()+"-shoppingCar";		
		Cookie[] cookies=req.getCookies();
		for(Cookie c:cookies){
			if(cookieName.equals(c.getName())){
			    String jsonData=c.getValue();
			    Object obj=JsonUtil.jsonStrToObject(jsonData, GoodsItem.class);
			    if(obj instanceof GoodsItem){
			        GoodsItem item=(GoodsItem)obj;
			        item.setAmount(item.getAmount()+goodsItem.getAmount());
			        cookieValue=JsonUtil.objectToJsonStr(item);
			        break;
			    }		       
			}			  		   
		}
		Cookie cookie=new Cookie(cookieName,cookieValue);
		cookie.setMaxAge(60*60);
		//cookie.setPath("/");
		resp.addCookie(cookie);
		*/
		User user=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
		log.info("user is "+user);
		if(null==user){
			log.info("用户未登录或登陆超时");
			respModel.setResultCode(ResultCode.ERROR.getCode());			
		}else{	
			SCarItem oldSCarItem= sCarItemMapper.getSCarItem(user.getUserUuid(), goodsItem.getGoodsName());
			if(null!=oldSCarItem){
				try {
					sCarItemMapper.modifySCarItem( oldSCarItem.getAmount()+goodsItem.getAmount(),user.getUserUuid(),goodsItem.getGoodsName());    
					respModel.setResultCode(ResultCode.OK.getCode());
					respModel.setResultMsg(ResultMsg.MODIFY_AMOUNT_SUCC.getMsg());
					log.info("添加商品-更新物品数目成功");
					
				} catch (Exception e) {
					e.printStackTrace();
					respModel.setResultCode(ResultCode.ERROR.getCode());
					respModel.setResultMsg(ResultMsg.MODIFY_AMOUNT_ERROR.getMsg());
				}
			}else{
				
				SCarItem sCarItem=new SCarItem();
				sCarItem.setUserUuid(user.getUserUuid());
				sCarItem.setGoodsName(goodsItem.getGoodsName());				
				sCarItem.setPrice(goodsItem.getPrice());
				sCarItem.setAmount(goodsItem.getAmount());
				sCarItem.setImgSrc(goodsItem.getImgSrc());
				sCarItem.setAttribution(goodsItem.getAttribution());
				try {
					sCarItemMapper.addSCarItem(sCarItem);
					respModel.setResultCode(ResultCode.OK.getCode());
					respModel.setResultMsg(ResultMsg.ADD_GOODS_SUCC.getMsg());				   
				    log.info("新添加商品成功");
				} catch (Exception e) {
					e.printStackTrace();
					respModel.setResultCode(ResultCode.ERROR.getCode());
					respModel.setResultMsg(ResultMsg.ADD_GOODS_ERROR.getMsg());
				}
			    
			}
			
		}
		return respModel;
	}

	public RespModel removeGoods(String goodsName,HttpServletRequest req) {
		log.info("removeGoods enter");	
		RespModel respModel =new RespModel();
		User user=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);		
		if(null==user){
			log.info("用户未登录或登陆超时");
			respModel.setResultCode(ResultCode.ERROR.getCode());			
		}else{
			try {			
				sCarItemMapper.deleteSCarItem(user.getUserUuid(), goodsName);
				respModel.setResultCode(ResultCode.OK.getCode());
				respModel.setResultMsg(ResultMsg.DEL_GOODS_SUCC.getMsg());
			} catch (Exception e) {
				e.printStackTrace();
				respModel.setResultCode(ResultCode.ERROR.getCode());
				respModel.setResultMsg(ResultMsg.DEL_GOODS_ERROR.getMsg());
			}
		}	
		return respModel;
	}

	public RespModel modifyGoods(GoodsItem goodsItem,HttpServletRequest req,HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	public RespModel showSCar(HttpServletRequest req) {
		RespModel respModel =new RespModel();
		User user=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
		if(null==user){
			log.info("用户未登录或登陆超时");
			respModel.setResultCode(ResultCode.ERROR.getCode());			
		}else{	
			try {
				List<SCarItem> list=sCarItemMapper.getSCarAllItems(user.getUserUuid());
				if(null==list||list.isEmpty()){
					log.info("购物车没有物品");
					respModel.setResultCode(ResultCode.OK.getCode());
					respModel.setResultMsg(ResultMsg.GET_SCAR_EMPTY.getMsg());
				}else{		
					List<GoodsItem> dataList=new ArrayList<GoodsItem>();
					Integer pprice = 0;
					for(SCarItem item:list){
						GoodsItem  goodsItem=new GoodsItem();
						goodsItem.setGoodsName(item.getGoodsName());
						goodsItem.setPrice(item.getPrice());
						goodsItem.setAmount(item.getAmount());
						goodsItem.setImgSrc(item.getImgSrc());
						goodsItem.setAttribution(item.getAttribution());
						pprice+=item.getAmount()*item.getPrice();
						dataList.add(goodsItem);
					}
					respModel.setListData(dataList);
					respModel.setNumberData(pprice);
					respModel.setResultCode(ResultCode.OK.getCode());
					respModel.setResultMsg(ResultMsg.GET_SCAR_SUCC.getMsg());
				}				
			} catch (Exception e) {
				log.info("查询购物车出错");
				e.printStackTrace();
				respModel.setResultCode(ResultCode.ERROR.getCode());
				respModel.setResultMsg(ResultMsg.GET_SCAR_ERROR.getMsg());
			}			
		}
		return respModel;
	}	
}
