package com.jason.mealorder.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.jason.mealorder.viewmodel.CommentModel;

@Service
public class CommentServiceImp implements CommentService {

	private static Logger log = Logger.getLogger(CommentServiceImp.class);
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public Map<String, Object> getAllComments(HttpServletRequest req) {
		log.info("开始加载评论信息");
		Map<String,Object> map=new HashMap<String, Object>();	
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
			map.put(SysConstant.DATA, JsonUtil.listToJsonStr(dataList));
			map.put(SysConstant.CODE, ResultCode.OK.getCode());
		} catch (Exception e) {
			map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
			log.info("加载评论信息失败");
		}
		return map;
	}

	@Override
	public Map<String, Object> addComment(String comments,String orderId,HttpServletRequest req) {
		log.info("发表评论信息");
		Map<String,Object> map=new HashMap<String, Object>();
		User curUser=(User)req.getSession().getAttribute(SysConstant.CURRENT_USER);
		if(curUser==null){
			log.info("用户未登录或登录超时");	
			map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
			return map;
		}else{		
			try {
				String orderStatus=orderMapper.getOrderStatusById(orderId);
				if(OrderStatus.COMMENTED.getStatus().equals(orderStatus)){
					log.info("已经评论过了");
					map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
					return map;
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
				map.put(SysConstant.CODE, ResultCode.OK.getCode());
				log.info("发表评论成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put(SysConstant.CODE, ResultCode.ERROR.getCode());
				log.info("发表评论失败");
			}			
		}		
		return map;
	}
}