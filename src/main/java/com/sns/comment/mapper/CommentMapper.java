package com.sns.comment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;

@Mapper
public interface CommentMapper {

	//input:x output:List<Map<String, Object>>
	public List<Map<String, Object>> selectCommentlist();
	
	public void insertComment(
			@Param("userId")int userId, 
			@Param("postId")int postId, 
			@Param("content")String content);
	
	public List<Comment> selectCommentList();
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentById(int commentId);
	
	public void deleteCommentByPostId(int postId);
}
