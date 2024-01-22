package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentView {
	//하나의 댓글
	private Comment comment;
	
	
	//댓글쓴이
	private UserEntity userEntity;	
	
}
