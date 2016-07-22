package com.jason.mealorder.mapper;

import java.util.List;

import com.jason.mealorder.entity.Comment;

public interface CommentMapper {

	public List<Comment> getCommentsByDishName(String dishName);
	public  void addCommentForDish(Comment comment);
}
