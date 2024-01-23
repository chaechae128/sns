package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	
	//output: 성공한 행의 개수
	public int selectLikeCountByPostIdUserId(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
	public void insertLikeByPostIdUserId(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId")int postId, 
			@Param("userId")int userId);
	
	public int selectLikeCountByPostId(int postId);
}
