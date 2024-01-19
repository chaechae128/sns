package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	//input: userId, postId, content  output:x
	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentListByPostId(){
		return commentMapper.selectCommentList();
	}
}
