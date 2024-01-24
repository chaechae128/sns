package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	
	//output: 성공한 행의 개수
//	public int selectLikeCountByPostIdUserId(
//			@Param("postId")int postId, 
//			@Param("userId")Integer userId);
//	
//	public int selectLikeCountByPostId(int postId);
	
	
	//like Count 쿼리 하나로 합치기
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId")int postId, 
			@Param("userId")Integer userId);
	
	public void insertLikeByPostIdUserId(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
}
