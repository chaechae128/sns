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
		if(likeMapper.selectLikeCountByPostIdUserId(postId, userId) != 0) { //like가 있으면
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else { //like가 없으면
			likeMapper.insertLikeByPostIdUserId(postId, userId);
		}
	}
	
	//input:postId   output:int 좋아요 개수
	public int selectLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
	public boolean isLikeByByPostIdUserId(int postId, int userId) {
		//mapper 에서 count 해서 받아온 값이 있으면 return true
		if(likeMapper.selectLikeCountByPostIdUserId(postId, userId) !=  0) {
			return true;
		} else {//없으면 return false
			return false;
		}
		
	}
	
}
