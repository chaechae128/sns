package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// View용 객체
// 글 1개와 매핑됨
@ToString
@Data
public class CardView {
	// 글 1개
	private PostEntity post;

	

	// 글쓴이 정보
	private UserEntity user;

	

	// (글에 대한) 댓글들
	private List<CommentView> commentList;
	
	// 좋아요 개수
	private int likeCount; 
	
	//로그인 된 사람이 좋아요를 누른지 여부
	//is~~ 로시작하면 getter이름이 바뀜 자바의 특성임
	private boolean filledLike;
	
	//로그인 된 사람의 피드 개수
	private int postCount;
	
}
