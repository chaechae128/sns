package com.sns.timeline.domain;

import com.sns.timeline.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// View용 객체
// 글 1개와 매핑됨
@ToString
public class CardView {
	// 글 1개
	private PostEntity post;

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	// 글쓴이 정보
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	// (글에 대한) 댓글들

	// 좋아요 개수

}
