package com.jason.mealorder.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.mealorder.common.JsonUtil;
import com.jason.mealorder.common.SysConstant;
import com.jason.mealorder.common.SysEnum.OrderStatus;
import com.jason.mealorder.common.SysEnum.ResultCode;
import com.jason.mealorder.entity.Comment;
import com.jason.mealorder.entity.User;
import com.jason.mealorder.mapper.CommentMapper;
import com.jason.mealorder.mapper.OrderMapper;
import com.jason.mealorder.respmodel.RespModel;
import com.jason.mealorder.viewmodel.CommentModel;

@Service
public class CommentServiceImp implements CommentService {

	private static Logger log = Logger.getLogger(CommentServiceImp.class);
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private OrderMapper orderMapper;
	public RespModel getAllComments(HttpServletRequest req,HttpServletResponse resp) {
		log.info("开始加载评论信息");
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		resp.setContentType("text/html;charset=UTF-8");
		RespModel respModel = new RespModel();	
		try {
			log.info("加载评论for"+req.getParameter("dishName"));
			List<Comment> list=commentMapper.getCommentsByDishName(req.getParameter("dishName"));
			List<CommentModel> dataList= new ArrayList<CommentModel>();							
			for(Comment c:list){
				CommentModel comment = new CommentModel();
				comment.setContent(c.getContent());
				comment.setDishName(c.getDishName());
				comment.setUsername(c.getUsername());
				log.info(c.getAddTime().toString());
				comment.setAddTime(c.getAddTime().toString().substring(0, 16));
				dataList.add(comment);
			}
			respModel.setJsonStrData(JsonUtil.listToJsonStr(dataList));
			respModel.setResultCode(ResultCode.OK.getCode());
		} catch (Exception e) {
			respModel.setResultCode(ResultCode.ERROR.getCode());
			log.error("加载评论信息失败");
		}
		return respModel;
	}

	public RespModel addComment(String comments,String orderId,HttpServletRequest req) {
		log.info("发表评论信息");
		RespModel respModel = new RespModel();
		User curUser=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
		if(curUser==null){
			log.info("用户未登录或登录超时");	
			respModel.setResultCode(ResultCode.ERROR.getCode());
			return respModel;
		}else{		
			try {
				String orderStatus=orderMapper.getOrderStatusById(orderId);
				if(OrderStatus.COMMENTED.getStatus().equals(orderStatus)){
					log.info("已经评论过了");
					respModel.setResultCode(ResultCode.ERROR.getCode());
					return respModel;
				}
				List<Comment> list=JsonUtil.jsonStrToList(comments, Comment.class);
				for(Comment comment:list){		
				   log.info(comment.getDishName());
				   log.info(comment.getContent());
				   if(null==comment.getContent()||0==comment.getContent().length()){
					   continue;
				   }
				   comment.setDishName(comment.getDishName().trim());
				   comment.setContent(comment.getContent().trim());
				   comment.setAddTime(new Timestamp(System.currentTimeMillis()));
				   comment.setUsername(curUser.getName());
				   commentMapper.addCommentForDish(comment);
				}
				orderMapper.updateOrderStatus(OrderStatus.COMMENTED.getStatus(), orderId);
				respModel.setResultCode(ResultCode.OK.getCode());
				log.info("发表评论成功");
			} catch (Exception e) {
				e.printStackTrace();
				respModel.setResultCode(ResultCode.ERROR.getCode());
				log.error("发表评论失败");
			}			
		}		
		return respModel;
	}
}
