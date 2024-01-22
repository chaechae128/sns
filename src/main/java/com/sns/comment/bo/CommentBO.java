package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;

	
	//input: userId, postId, content  output:x
	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentListByPostId(){
		return commentMapper.selectCommentList();
	}
	
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		//결과 리스트 만들기
		List<CommentView> commentViewList = new ArrayList<>();
		//글에 해당하는 댓글 목록 가져오기 List<Comment>
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		
		//반복문 순회 Comment -> CommentView => 리스트에 넣기
		
		for(Comment comment: commentList) {
			CommentView commentView = new CommentView();
			
			//commentView 에 comment, userId 넣기
			commentView.setComment(comment);
			UserEntity user = userBO.getUerEntityById(comment.getUserId());
			commentView.setUserEntity(user);
			commentViewList.add(commentView);
		}
		//결과 리스트 리턴
		
		return commentViewList;
	}
	
	public void deleteCommentById(int commentId) {
		commentMapper.deleteCommentById(commentId);
	}
}
