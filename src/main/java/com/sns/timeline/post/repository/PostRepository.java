package com.sns.timeline.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.timeline.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{
	
	public List<PostEntity> findAllByOrderByIdDesc();
	
	
}
