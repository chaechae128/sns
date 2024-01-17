package com.sns.timeline.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.timeline.post.entity.PostEntity;
import com.sns.timeline.post.repository.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByDesc();
	}
}
