package com.sns.user.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.timeline.domain.CardView;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProfileView {
		// 글 리스트
		private List<PostEntity> postList;

		//로그인 된 사람 정보
		private UserEntity user;
		
		//로그인 된 사람의 피드 개수
		private int postCount;
		
		
}
