package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	//input:postId, userId	output:void
	public void LikeToggle(int postId, int userId) {
		
		// like가 있으면 
		//	->삭제
		// like가 없으면
		// 	->추가
		
		//조회
		if(likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) != 0) { //like가 있으면
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else { //like가 없으면
			likeMapper.insertLikeByPostIdUserId(postId, userId);
		}
	}
	
	//input:postId   output:int 좋아요 개수
	public int selectLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	//input: postId, userId(null or)  output:boolean
	public boolean getLikeByPostIdUserId(int postId, Integer userId) {
		//mapper 에서 count 해서 받아온 값이 있으면 return true
		if(userId == null) {
			return false;
		} 
		// 로그인 0보다 크면(1이면) 채운다. 그렇지 않으면 false
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
		
	}
	
}
